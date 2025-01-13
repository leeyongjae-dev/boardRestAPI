package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BoardReq;
import com.example.demo.model.BoardRes;
import com.example.demo.model.CommonBoardRes;
import com.example.demo.service.BoardService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/board")
@Tag(name="게시판 API")
public class BoardController {

	@Autowired
	BoardService boardService;

	/**
	 * [게시판] 목록 조회
	 * @param boardListReq
	 * @return
	 */
	@GetMapping(value = {"/list"})
	public CommonBoardRes getBoardList(BoardReq boardReq) {
		CommonBoardRes response = new CommonBoardRes();

		Integer resultCount = 0;
		List<BoardRes> result = new ArrayList<BoardRes>();

		try {
			// [0]. 게시판 목록 Data 갯수 조회
			resultCount = boardService.selectBoardListCount(boardReq);

			if(resultCount > 0) {
				// [1]. 게시판 목록 Data 조회
				boardReq.setStartRow(((boardReq.getCurrPage() - 1) * boardReq.getRowCount() + 1) - 1);
				boardReq.setEndRow(boardReq.getRowCount());
				result = boardService.selectBoardList(boardReq);
			}
			// [2]. 응답값 셋팅
			response.setTotalCount(resultCount);
			response.setList(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	/**
	 * [게시판] 상세 조회
	 * @param boardListReq
	 * @return
	 */
	@GetMapping(value = {"/detail"})
	public BoardRes getBoardDetail(BoardReq boardReq) {
		BoardRes response = new BoardRes();
		try {
			// [0]. 게시판 상세 조회
			response = boardService.selectBoardDetail(boardReq);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

	/**
	 * [게시판] 등록
	 * @param boardReq
	 * @return
	 */
	@PostMapping(value = {"/insert"})
	public int insertBoard(@RequestBody BoardReq boardReq) {
		int resultCnt = 0;
		try {
			// [0]. 게시판 등록
			resultCnt = boardService.insertBoard(boardReq);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return resultCnt;
	}

	/**
	 * [게시판] 수정
	 * @param boardReq
	 * @return
	 */
	@PutMapping(value = {"/update"})
	public int updateBoard(BoardReq boardReq) {
		int resultCnt = 0;
		try {
			// [0]. 게시판 수정
			resultCnt = boardService.updateBoard(boardReq);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return resultCnt;
	}

	/**
	 * /**
	 * [게시판] 삭제
	 * @param boardReq
	 * @return
	 */
	@DeleteMapping(value = {"/delete"})
	public int deleteBoard(BoardReq boardReq) {
		int resultCnt = 0;
		try {
			// [0]. 게시판 삭제
			resultCnt = boardService.deleteBoard(boardReq);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return resultCnt;
	}

}
