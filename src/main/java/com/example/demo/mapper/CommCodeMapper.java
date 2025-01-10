package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.CommCodeReq;
import com.example.demo.model.CommCodeRes;

@Mapper
public interface CommCodeMapper {

	List<CommCodeRes> selectCommCodeList(CommCodeReq commCodeReq);

}
