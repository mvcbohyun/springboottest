package com.bhjang.mvc.domain;


import java.util.Date;

import lombok.Data;

@Data
public class Board {
	
	private int boardSeq;
	private String boardType;
	private String title;
	private String content;
	private int viewCount;
	private Date regDate;
	private boolean delYn;
}
