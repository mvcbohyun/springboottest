package com.bhjang.mvc.parameter;




import com.bhjang.mvc.domain.BoardType;

import lombok.Data;

@Data
public class BoardSearchParameter {
	

	private String keyword;
	private BoardType[] boardTypes;
}
