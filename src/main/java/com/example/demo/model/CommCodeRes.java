package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CommCodeRes {
	@JsonProperty("grp_cd")
	private String grp_cd;

	@JsonProperty("comm_cd")
	private String comm_cd;

	@JsonProperty("comm_cd_nm")
	private String comm_cd_nm;

	@JsonProperty("comm_cd_val")
	private String comm_cd_val;

	@JsonProperty("add1")
	private String add1;

	@JsonProperty("add2")
	private String add2;

	@JsonProperty("add3")
	private String add3;

	@JsonProperty("ord")
	private String ord;

	@JsonProperty("del_yn")
	private String del_yn;

	@JsonProperty("reg_dt")
	private String reg_dt;
}
