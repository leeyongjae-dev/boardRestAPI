package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.model.BoardReq;
import com.example.demo.model.BoardRes;
import com.example.demo.model.FileDto;

import jakarta.servlet.http.HttpServletRequest;

@Service
@Transactional(rollbackFor = {Exception.class})
public class BoardService {

	@Autowired
	BoardMapper boardMapper;

	@Autowired
	FileService fileService;

	private static String BOARD_FILE_REF_TBL = "bt_tb_board";

	public Integer selectBoardListCount(BoardReq boardReq) {
		return boardMapper.selectBoardListCount(boardReq);
	}

	public List<BoardRes> selectBoardList(BoardReq boardReq) {
		return boardMapper.selectBoardList(boardReq);
	}

	public BoardRes selectBoardDetail(BoardReq boardReq) {

		if(boardReq.getBoardNo() != null) {
			boardMapper.updateBoardViewCnt(boardReq);
		}

		return boardMapper.selectBoardDetail(boardReq);
	}

	public int insertBoard(HttpServletRequest request, BoardReq boardReq) {
		int result = 0;

		result = boardMapper.insertBoard(boardReq);

		if(result > 0 && boardReq.getBoardNo() != null) {
			try {
				List<FileDto> fileDtoList = fileService.savePhyFileReturnFileList(request, "", BOARD_FILE_REF_TBL);

				if(fileDtoList.size() > 0) {
					for(FileDto fileDto : fileDtoList) {
						fileDto.setRefTbl(BOARD_FILE_REF_TBL);
						fileDto.setRefPk(Integer.toString(boardReq.getBoardNo()));
					}
					result += fileService.saveDBFile(fileDtoList);
				}

				result = boardReq.getBoardNo();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return result;
	}

	public int updateBoard(HttpServletRequest request, BoardReq boardReq) {
		int result = 0;

		result = boardMapper.updateBoard(boardReq);

		if(result > 0) {

			try {

				List<Integer> delFileList = boardReq.getDelFileList();

				if(delFileList.size() > 0) {
					for(Integer delFileNo : delFileList) {
						FileDto fileDto = new FileDto();
						fileDto.setFileNo(delFileNo);
						fileDto = fileService.selectFile(fileDto);
						fileService.deletePhyFile(fileDto);

						result += fileService.deleteDBFile(fileDto);
					}
				}

				List<FileDto> fileDtoList = fileService.savePhyFileReturnFileList(request, "", BOARD_FILE_REF_TBL);

				if(fileDtoList.size() > 0) {
					for(FileDto fileDto : fileDtoList) {
						fileDto.setRefTbl(BOARD_FILE_REF_TBL);
						fileDto.setRefPk(Integer.toString(boardReq.getBoardNo()));
					}
					result += fileService.saveDBFile(fileDtoList);
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return result;
	}

	public int deleteBoard(BoardReq boardReq) {
		int result = 0;

		BoardRes detail = this.selectBoardDetail(boardReq);

		try {

			if(detail != null) {
				BoardReq req = new BoardReq();
				req.setBoardNo(detail.getBoardNo());
				result += boardMapper.deleteBoard(req);

				FileDto fileReq = new FileDto();
				fileReq.setRefTbl(BOARD_FILE_REF_TBL);
				fileReq.setRefPk(Integer.toString(detail.getBoardNo()));
				detail.setFileList(fileService.selectFileList(fileReq));

				if(detail.getFileList().size() > 0) {
					for(FileDto file : detail.getFileList()) {
						fileService.deletePhyFile(file);
						result += fileService.deleteDBFile(file);
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

}
