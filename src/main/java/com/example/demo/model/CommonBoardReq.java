package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CommonBoardReq {

	@JsonProperty("currPage")
	@Schema(example = "1")
	private int currPage;

	@JsonProperty("rowCount")
	@Schema(example = "10")
	private int rowCount;

	@JsonProperty("startRow")
	@Schema(example = "1", hidden = true)
	private Integer startRow;

	@JsonProperty("endRow")
	@Schema(example = "10", hidden = true)
	private int endRow;

}
