package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FileReq {

	@JsonProperty("delFileList")
	private List<Integer> delFileList;

}
