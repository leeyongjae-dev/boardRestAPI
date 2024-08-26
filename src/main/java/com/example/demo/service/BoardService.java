package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.model.BoardListReq;
import com.example.demo.model.BoardListRes;

@Service
public class BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	public List<BoardListRes> selectBoardList(BoardListReq boardListReq) {
		return boardMapper.selectBoardList(boardListReq);
	}

}
