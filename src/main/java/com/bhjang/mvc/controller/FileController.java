package com.bhjang.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhjang.configuration.GlobalConfig;
import com.bhjang.configuration.http.BaseResponse;
import com.bhjang.mvc.domain.Board;
import com.bhjang.mvc.domain.MySQLPageRequest;
import com.bhjang.mvc.domain.PageRequestParameter;
import com.bhjang.mvc.parameter.BoardSearchParameter;

import java.util.List;

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
	
	/*
	 * 업로드 리턴
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "업로드", notes = "")
	public BaseResponse<Boolean> save() {
		logger.info("config : {}",config);
		String uploadFilePath = config.getUploadFilePath();
		logger.info("uploadFilePath : {}",uploadFilePath);
		return new BaseResponse<Boolean>(true);
	}
}
