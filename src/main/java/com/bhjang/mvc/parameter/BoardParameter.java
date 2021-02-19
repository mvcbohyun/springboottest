package com.bhjang.mvc.parameter;

import com.bhjang.mvc.domain.BoardType;

import lombok.Data;

@Data
public class BoardParameter {
	
	private int boardSeq;
    private BoardType boardType;
	private String title;
	private String content;
	private boolean delYn;
	
	public BoardParameter() {
		
	}
	public BoardParameter(String title, String content) {
		this.title = title ;
		this.content =content;
		
	}
}
