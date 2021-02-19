package com.bhjang.mvc.domain;

import lombok.Data;

@Data
public class Board {
	
	private int boardSeq;
	private BoardType boardType;
	private String title;
	private String content;
	private String regDate;
	private boolean delYn;
}
