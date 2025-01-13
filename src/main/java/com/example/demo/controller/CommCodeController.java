package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CommCodeReq;
import com.example.demo.model.CommCodeRes;
import com.example.demo.service.CommCodeService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/comm/code")
@Tag(name="공통코드 API")
public class CommCodeController {

	@Autowired
	CommCodeService commCodeService;

	@GetMapping(value = {"/list"})
	public List<CommCodeRes> getCommCodeList(CommCodeReq commCodeReq) {
		List<CommCodeRes> result = new ArrayList<CommCodeRes>();

		try {
			// [1]. 공통 코드 목록 Data 조회
			result = commCodeService.selectCommCodeList(commCodeReq);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

		return result;
	}

}
