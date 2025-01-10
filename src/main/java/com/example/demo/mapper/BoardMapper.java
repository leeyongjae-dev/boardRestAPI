package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.BoardReq;
import com.example.demo.model.BoardRes;

@Mapper
public interface BoardMapper {

	Integer selectBoardListCount(BoardReq boardListReq);

	List<BoardRes> selectBoardList(BoardReq boardListReq);

	BoardRes selectBoardDetail(BoardReq boardReq);

	int insertBoard(BoardReq boardReq);

	int updateBoard(BoardReq boardReq);

	int deleteBoard(BoardReq boardReq);

	void updateBoardViewCnt(BoardReq boardReq);

}
