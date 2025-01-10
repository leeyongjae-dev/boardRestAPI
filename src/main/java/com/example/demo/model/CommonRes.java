package com.example.demo.model;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CommonRes {

	Integer totalCount;

	List<? extends Object> list;

}
