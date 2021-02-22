package com.bhjang.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bhjang.configuration.GlobalConfig;
import com.bhjang.configuration.exception.BaseException;
import com.bhjang.configuration.http.BaseResponse;
import com.bhjang.configuration.http.BaseResponseCode;
import com.bhjang.mvc.domain.Board;
import com.bhjang.mvc.domain.MySQLPageRequest;
import com.bhjang.mvc.domain.PageRequestParameter;
import com.bhjang.mvc.parameter.BoardSearchParameter;
import com.bhjang.mvc.parameter.UploadFileParameter;
import com.bhjang.mvc.service.UploadFileService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/*
 * 파일 컨트롤러
 * @author 장보현
 */
@RestController
@RequestMapping("/file")
@Api(tags = "파일 API")
public class FileController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GlobalConfig config;
	
	@Autowired
	private UploadFileService uploadFileService;
	/*
	 * 업로드 리턴
	 * @return
	 */
	@PostMapping("/save")
	@ApiOperation(value = "업로드", notes = "")
	public BaseResponse<Boolean> save(@RequestParam("uploadFile") MultipartFile multipartFile) {
		logger.info("config : {}",config);
		if(multipartFile == null || multipartFile.isEmpty()) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL);
			}
		// 날짜 폴더를 추가
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		String uploadFilePath = config.getUploadFilePath()+ currentDate + "/";
		logger.info("uploadFilePath : {}",uploadFilePath);
		String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1,multipartFile.getOriginalFilename().length());
		String filename = UUID.randomUUID().toString() +"." +prefix;
		logger.info("filename : {}", filename);
		File folder = new File(uploadFilePath);
		// 폴더가 없다면 생성 
		if(!folder.isDirectory()) {
			folder.mkdirs();
		}
		String pathname = uploadFilePath +filename;
		String resourcePathname = config.getUploadResourcePath() + currentDate +"/"+filename;
		File dest = new File(pathname);
		
			try {
				multipartFile.transferTo(dest);
				// 파일업로드 된 후 DB에 저장
				UploadFileParameter parameter = new UploadFileParameter();
				//컨텐츠 종류 
				parameter.setContentType(multipartFile.getContentType());
				//원본파일명
				parameter.setOriginalFilename(multipartFile.getOriginalFilename());
				//저장파일명
				parameter.setFilename(filename);
				// 실제파일 저장경로
				parameter.setPathname(pathname);
				// 파일 크기
				parameter.setSize((int)multipartFile.getSize());
				// static resouce 접근 경로
				parameter.setResourcePathname(resourcePathname);
				uploadFileService.save(parameter);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		return new BaseResponse<Boolean>(true);
	}
}
