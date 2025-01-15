package com.example.demo.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.mapper.FileMapper;
import com.example.demo.model.FileDto;
import com.example.demo.model.FileReq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
@Transactional(rollbackFor = {Exception.class})
public class FileService {

	@Autowired
	FileMapper fileMapper;

	@Value("${file.root.path}")
	private String fileRootPath;

	@Value("${file.separator}")
	private String fileSeparator;

	@Value("${file.mkdirs.path}")
	private String fileMkdirsPath;

	public FileDto selectFile(FileDto fileDto) throws Exception {
		FileDto file = fileMapper.selectFile(fileDto);
		return file;
	}

	public List<FileDto> selectFileList(FileDto fileDto) throws Exception {
		List<FileDto> listFile = fileMapper.selectFileList(fileDto);
		return listFile;
	}

	public void deletePhyFile(FileDto fileDto) throws Exception {
		String rootPath = fileRootPath;
		String savePath = fileDto.getSavePath();
		String fileNm = fileDto.getSaveFileNm();

		String strFilePath = rootPath  + savePath + fileNm;

		File delFile = new File(strFilePath);

		delFile.delete();
	}

	public void downloadFile(HttpServletRequest request, HttpServletResponse response, FileDto fileDto) throws Exception {
		FileDto newFileDto = this.selectFile(fileDto);
		if (newFileDto != null) {
			String rootPath = fileRootPath;
			String savePath = newFileDto.getSavePath();
			String fileNm = newFileDto.getSaveFileNm();

			String strFilePath = rootPath  + savePath + fileNm;
			String strOrgnlFileNm = newFileDto.getOriginFileNm();

			this.realDownloadPhyFile(request, response, strFilePath, strOrgnlFileNm);
		}
	}

	public void realDownloadPhyFile(HttpServletRequest request, HttpServletResponse response, String strFilePath, String strOrgnlFileNm) throws Exception {

		InputStream inputStream = null;

		try {

			File file = new File(strFilePath);

			String strMimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (strMimeType == null) {
				strMimeType = "application/octet-stream";
			}

			strOrgnlFileNm = this.getDisposition(request, strOrgnlFileNm);

			response.setHeader("Content-Transfer-Encoding", "binary;");
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + strOrgnlFileNm + "\"");
			response.setContentType(strMimeType);
			response.setContentLength((int) file.length());

			inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	public List<FileDto> savePhyFileReturnFileList(HttpServletRequest request, String setStrFileDivNm, String folderNm) throws Exception {

		List<FileDto> listFileDto = new ArrayList<FileDto>();
		List<MultipartFile> listMultipartFile = new ArrayList<MultipartFile>();
		String strFolderName = "";

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		Iterator<String> iterator = multipartRequest.getFileNames();
		while(iterator.hasNext()) {

			String fileParameterName = iterator.next();
			strFolderName = folderNm;
			listMultipartFile = multipartRequest.getFiles(fileParameterName);

			int iFileCnt = listMultipartFile.size();
			if (iFileCnt > 0) {

				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				Calendar calendar = Calendar.getInstance();
				String strDatePath = format.format(calendar.getTime());
				String strRootPath = fileRootPath;
				String strSeparator = fileSeparator;
				String strStrgFilePathAddr = strSeparator + strFolderName + strSeparator + strDatePath + strSeparator;

				String strFullFileFolderPath = strRootPath + strSeparator + strFolderName + strSeparator + strDatePath + strSeparator;

				this.makeFileFolder(strFullFileFolderPath);

				for(MultipartFile multipartFile : listMultipartFile) {

					if (!multipartFile.isEmpty()) {

						String strFileExt = multipartFile.getOriginalFilename();
						if (strFileExt != null && !"".equals(strFileExt)) {
							strFileExt = strFileExt.substring(strFileExt.lastIndexOf(".") + 1, strFileExt.length()).toLowerCase().trim();
						} else {
							strFileExt = "";
						}

						String strOrgnlFileNm = multipartFile.getOriginalFilename();
						String strUuid = UUID.randomUUID().toString();

						String strStrgFileNm = strUuid + "." + strFileExt;
						File file = new File(strFullFileFolderPath, strStrgFileNm);
						file.setReadable(true);
						file.setWritable(true);

						org.springframework.util.FileCopyUtils.copy(multipartFile.getBytes(), file);

						//DB저장시 등에 이용할 FileDto
						FileDto fileDto = new FileDto();

						fileDto.setFile(file);						// 파일 객체
						fileDto.setOriginFileNm(strOrgnlFileNm);	// 소스 파일 명 (사용자가 업로드한 파일 명)
						fileDto.setSaveFileNm(strStrgFileNm);		// 논리 파일 명
						fileDto.setSavePath(strStrgFilePathAddr);	// 파일 경로
						fileDto.setSize(multipartFile.getSize());	// 파일 크기
						fileDto.setExt(strFileExt);					// 파일 확장자

						listFileDto.add(fileDto);

					}
				}
			}
		}

		return listFileDto;
	}

	public int saveDBFile(List<FileDto> listFile) throws Exception {
		Integer result = 0;
		if (listFile != null && listFile.size() > 0) {
			for (int i = 0; i < listFile.size(); i++) {
				FileDto fileDto = listFile.get(i);
				result = fileMapper.insertFile(fileDto);
			}
		}
		return result;
	}

	public int deleteDBFile(FileDto fileDto) {
		return fileMapper.deleteFile(fileDto);
	}

	public int deleteFileList(FileReq fileReq) {
		int result = 0;

		try {
			List<Integer> delFileList = fileReq.getDelFileList();

			if(delFileList.size() > 0) {
				for(Integer delFileNo : delFileList) {
					FileDto fileDto = new FileDto();
					fileDto.setFileNo(delFileNo);
					fileDto = this.selectFile(fileDto);
					this.deletePhyFile(fileDto);

					result += this.deleteDBFile(fileDto);
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	/*****************************************************************************************************************************************************************************************************************************/
	/*****************************************************************************************************************************************************************************************************************************/
	/*****************************************************************************************************************************************************************************************************************************/
	/*****************************************************************************************************************************************************************************************************************************/
	private void makeFileFolder(String strFullFileFolderPath) throws Exception {

		String strMakeDir =  strFullFileFolderPath;
		File dir = new File(strMakeDir);
		dir.setExecutable(true);
		dir.setReadable(true);
		dir.setWritable(true);

		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

	}

	public static String getBrowser(String userAgent) {
		String browser = "";

		if(userAgent.indexOf("Trident") > -1) { // IE
			browser = "MSIE";
		} else if(userAgent.indexOf("Edge") > -1) { // Edge
			browser = "Edge";
		} else if(userAgent.indexOf("Whale") > -1) { // Naver Whale
			browser = "Whale";
		} else if(userAgent.indexOf("Opera") > -1 || userAgent.indexOf("OPR") > -1) { // Opera
			browser = "Opera";
		} else if(userAgent.indexOf("Firefox") > -1) { // Firefox
			browser = "Firefox";
		} else if(userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1 ) { // Safari
			browser = "Safari";
		} else if(userAgent.indexOf("Chrome") > -1) { // Chrome
			browser = "Chrome";
		}

		return browser;
	}

	public static String getDisposition(HttpServletRequest request, String filename) {
		String browser = getBrowser(request.getHeader("User-Agent"));
		String encodedFilename = "";

		try {
			if (browser.contains("MSIE")) {
				encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");

			} else if(browser.contains("Firefox")) {
				encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";

			} else if(browser.contains("Opera")) {
				encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";

			} else if(browser.contains("Chrome")) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < filename.length(); i++) {
					char c = filename.charAt(i);
					if(c > '~') {
						sb.append(URLEncoder.encode("" + c, "UTF-8"));
					} else {
						sb.append(c);
					}
				}
				encodedFilename = sb.toString();

			} else if(browser.contains("Safari")) {
				encodedFilename = filename;

			} else if(browser.contains("Mozilla")) {
				encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";

			} else {
				encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20"); // MSIE 외 모든 브라우저

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return encodedFilename;
	}

}
