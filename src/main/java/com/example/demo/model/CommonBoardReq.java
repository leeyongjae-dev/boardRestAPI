package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CommonBoardReq {

	@JsonProperty("currPage")
	private int currPage;

	@JsonProperty("rowCount")
	private int rowCount;

	@JsonProperty("startRow")
	private Integer startRow;

	@JsonProperty("endRow")
	private int endRow;

}
