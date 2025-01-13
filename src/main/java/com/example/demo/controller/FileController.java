package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/file")
@Tag(name="파일 API")
public class FileController {

	@GetMapping("/download")
	public void downloadFile(HttpServletRequest request,
			HttpServletResponse response/* , FileDto fileDto */) {

	}

	@DeleteMapping("/delete")
	public int deleteFile(HttpServletRequest request,
			HttpServletResponse response/* , FileDto fileDto */) {
		return 0;
	}

}
