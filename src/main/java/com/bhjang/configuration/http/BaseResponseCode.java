package com.bhjang.configuration.http;

public enum BaseResponseCode {

	SUCCESS, // 성공
	ERROR, // 실패
	LOGIN_REQUIRE, // 로그인 체크
	DATA_IS_NULL , // null 체크
	VALIDATE_REQUIRED, // 필수 체크
	UPLOAD_FILE_IS_NULL // 업로드 파일 null 체크
	;

}
