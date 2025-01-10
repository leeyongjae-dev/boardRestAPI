package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.CommCodeMapper;
import com.example.demo.model.CommCodeReq;
import com.example.demo.model.CommCodeRes;

@Service
public class CommCodeService {

	@Autowired
	CommCodeMapper commCodeMapper;

	public List<CommCodeRes> selectCommCodeList(CommCodeReq commCodeReq) {
		return commCodeMapper.selectCommCodeList(commCodeReq);
	}

}
