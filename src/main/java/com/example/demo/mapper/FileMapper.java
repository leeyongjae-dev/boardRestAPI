package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.FileDto;

@Mapper
public interface FileMapper {

	List<FileDto> selectFileList(FileDto fileDto);

	FileDto selectFile(FileDto fileDto);

	int insertFile(FileDto fileDto);

	int deleteFile(FileDto fileDto);

}
