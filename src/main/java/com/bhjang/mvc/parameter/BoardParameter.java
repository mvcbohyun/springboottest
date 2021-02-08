package com.bhjang.mvc.parameter;

import lombok.Data;

@Data
public class BoardParameter {
	
	private int boardSeq;
	private String title;
	private String content;
	
	public BoardParameter() {
		
	}
	public BoardParameter(String title, String content) {
		this.title = title ;
		this.content =content;
		
	}
}
