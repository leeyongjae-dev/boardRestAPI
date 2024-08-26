package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BoardListRes {
	@JsonProperty("title")
	private String title;
}
