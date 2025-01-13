package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
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
	private String boardNo;

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
