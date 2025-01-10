package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CommCodeReq {

	@JsonProperty("grpCd")
	private String grpCd;

	@JsonProperty("commCd")
	private String commCd;

}
