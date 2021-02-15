package com.bhjang.mvc.parameter;



import java.util.List;

import com.bhjang.mvc.domain.BoardType;

import lombok.Data;

@Data
public class BoardSearchParameter {
	

	private String keyword;
	private List<BoardType> boardTypes;
}
