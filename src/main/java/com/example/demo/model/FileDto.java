package com.example.demo.model;

import java.io.File;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FileDto {

	private Integer fileNo;

	@Schema(hidden = true)
	private String originFileNm;

	@Schema(hidden = true)
	private String saveFileNm;

	@Schema(hidden = true)
	private String savePath;

	@Schema(hidden = true)
	private String ext;

	@Schema(hidden = true)
	private long size;

	@Schema(hidden = true)
	private String refTbl;

	@Schema(hidden = true)
	private String refPk;

	@Schema(hidden = true)
	private String refKey;

	@Schema(hidden = true)
	private Integer downloadCnt;

	@Schema(hidden = true)
	private Integer ord;

	@Schema(hidden = true)
	private String regDt;

	@Schema(hidden = true)
	private File file;

}