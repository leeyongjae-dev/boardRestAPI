package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.model.BoardReq;
import com.example.demo.model.BoardRes;

@Service
public class BoardService {

	@Autowired
	BoardMapper boardMapper;

	public Integer selectBoardListCount(BoardReq boardReq) {
		return boardMapper.selectBoardListCount(boardReq);
	}

	public List<BoardRes> selectBoardList(BoardReq boardReq) {
		return boardMapper.selectBoardList(boardReq);
	}

	public BoardRes selectBoardDetail(BoardReq boardReq) {

		if(!"".equals(boardReq.getBoardNo())) {
			boardMapper.updateBoardViewCnt(boardReq);
		}

		return boardMapper.selectBoardDetail(boardReq);
	}

	public int insertBoard(BoardReq boardReq) {
		return boardMapper.insertBoard(boardReq);
	}

	public int updateBoard(BoardReq boardReq) {
		return boardMapper.updateBoard(boardReq);
	}

	public int deleteBoard(BoardReq boardReq) {
		return boardMapper.deleteBoard(boardReq);
	}

}
