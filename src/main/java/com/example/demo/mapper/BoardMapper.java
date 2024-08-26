package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.BoardListReq;
import com.example.demo.model.BoardListRes;

@Mapper
public interface BoardMapper {

	List<BoardListRes> selectBoardList(BoardListReq boardListReq);
	
}
