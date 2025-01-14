package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BoardReq extends CommonBoardReq {

	@JsonProperty("searchKeyword")
	private String searchKeyword;

	@JsonProperty("searchText")
	private String searchText;

	@JsonProperty("searchOrder")
	private String searchOrder;

	@JsonProperty("boardNo")
	private Integer boardNo;

	@JsonProperty("categoryCd")
	private String categoryCd;

	@JsonProperty("title")
	private String title;

	@JsonProperty("cont")
	private String cont;

	@JsonProperty("writerNm")
	private String writerNm;

	@JsonProperty("password")
	private String password;

	@JsonProperty("delFileList")
	private List<Integer> delFileList;

}
