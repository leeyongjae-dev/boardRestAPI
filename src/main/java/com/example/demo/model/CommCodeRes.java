package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CommCodeRes {
	@JsonProperty("grpCd")
	private String grpCd;

	@JsonProperty("commCd")
	private String commCd;

	@JsonProperty("commCdNm")
	private String commCdNm;

	@JsonProperty("commCdVal")
	private String commCdVal;

	@JsonProperty("add1")
	private String add1;

	@JsonProperty("add2")
	private String add2;

	@JsonProperty("add3")
	private String add3;

	@JsonProperty("ord")
	private String ord;

	@JsonProperty("delYn")
	private String delYn;

	@JsonProperty("regDt")
	private String regDt;
}
