package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BoardReq {

	@JsonProperty("searchText")
	private String searchText;

	@JsonProperty("searchOrder")
	private String searchOrder;

	@JsonProperty("boardNo")
	private String boardNo;

	@JsonProperty("currPage")
	private int currPage;

	@JsonProperty("rowCount")
	private int rowCount;

	@JsonProperty("startRow")
	private Integer startRow;

	@JsonProperty("endRow")
	private int endRow;

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

}
