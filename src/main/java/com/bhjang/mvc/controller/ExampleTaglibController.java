package com.bhjang.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bhjang.mvc.domain.BoardType;
import com.bhjang.mvc.parameter.BoardSearchParameter;

@Controller
@RequestMapping("/example/taglib/")
public class ExampleTaglibController {
	
	@RequestMapping("/search")
	public void search(BoardSearchParameter parameter, Model model) {
		model.addAttribute("boardTypes",BoardType.values());
		model.addAttribute("parameter",parameter);
	}
	
	
}
