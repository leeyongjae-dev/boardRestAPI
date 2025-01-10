package com.example.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class CommonBoardRes {

	Integer totalCount;

	List<BoardRes> list;

}
