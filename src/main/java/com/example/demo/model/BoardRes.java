package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BoardRes {

	@JsonProperty("board_no")
	private Integer board_no;

	@JsonProperty("category_cd")
	private String category_cd;

	@JsonProperty("category_nm")
	private String category_nm;

	@JsonProperty("title")
	private String title;

	@JsonProperty("cont")
	private String cont;

	@JsonProperty("writer_nm")
	private String writer_nm;

	@JsonProperty("password")
	private String password;

	@JsonProperty("view_cnt")
	private Integer view_cnt;

	@JsonProperty("reg_dt")
	private String reg_dt;

	@JsonProperty("mod_dt")
	private String mod_dt;

	@JsonProperty("new_yn")
	private String new_yn;

	@JsonProperty("row_num")
	private Integer row_num;

}
