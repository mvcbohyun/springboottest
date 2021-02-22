package com.bhjang.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhjang.mvc.parameter.UploadFileParameter;
import com.bhjang.mvc.repository.UploadFileRepository;

@Service
public class UploadFileService {

	@Autowired
	private UploadFileRepository repository;
	
	/*
	 * 등록 처리 .
	 * @param parameter
	 */
	public void save(UploadFileParameter parameter) {
		
		repository.save(parameter);
		
	}
}
