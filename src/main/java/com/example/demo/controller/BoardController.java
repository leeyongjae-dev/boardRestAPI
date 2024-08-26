package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BoardListReq;
import com.example.demo.model.BoardListRes;
import com.example.demo.service.BoardService;

@RestController
@RequestMapping("/api/board")
@CrossOrigin(origins = "http://localhost:3005") // 특정 도메인 허용
public class BoardController {
	
	@Autowired
	BoardService boardService;
	 
	@GetMapping(value = {"/list"})
	public List<BoardListRes> getBoardList(BoardListReq boardListReq) {
		
		List<BoardListRes> result =  new ArrayList<BoardListRes>();
		
		/* 조회 */
		try {
			result = boardService.selectBoardList(boardListReq);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		return result;
	}
	

}
