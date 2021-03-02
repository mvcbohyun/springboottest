package com.bhjang.mvc.domain;
/**
 * 메뉴 게시판 종류
 * @author 장보현
 *
 */
public enum MenuType{
	
	community(BoardType.COMMUNITY),
	notice(BoardType.NOTICE),
	faq(BoardType.FAQ),
	inquity(BoardType.INQUITY),
	;
	
	private BoardType boardType;
	
	MenuType(BoardType boardType) {
		this.boardType= boardType;
		
	}
}
