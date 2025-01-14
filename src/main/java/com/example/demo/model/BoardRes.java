package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BoardRes {

	@JsonProperty("boardNo")
	private Integer boardNo;

	@JsonProperty("categoryCd")
	private String categoryCd;

	@JsonProperty("categoryNm")
	private String categoryNm;

	@JsonProperty("title")
	private String title;

	@JsonProperty("cont")
	private String cont;

	@JsonProperty("writerNm")
	private String writerNm;

	@JsonProperty("password")
	private String password;

	@JsonProperty("viewCnt")
	private Integer viewCnt;

	@JsonProperty("regDt")
	private String regDt;

	@JsonProperty("modDt")
	private String modDt;

	@JsonProperty("newYn")
	private String newYn;

	@JsonProperty("rowNum")
	private Integer rowNum;

	@JsonProperty("fileYn")
	private String fileYn;

	@JsonProperty("fileList")
	List<FileDto> fileList;

}
