package com.bhjang.mvc.repository;

import org.springframework.stereotype.Repository;

import com.bhjang.mvc.parameter.UploadFileParameter;

/*
 * 업로드 파일 Repository
 * @author 장보현
 */
@Repository
public interface UploadFileRepository {

	void save(UploadFileParameter parameter);
}
