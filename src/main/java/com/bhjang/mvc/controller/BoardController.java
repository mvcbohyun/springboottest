package com.bhjang.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhjang.mvc.domain.Board;
import com.bhjang.mvc.repository.BoardRepository;
import com.bhjang.mvc.service.BoardService;
/*
 * 게시판  컨트롤러
 * @author 장보현
 */
@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/*
	 * 목록 리턴
	 * @return
	 */
	@GetMapping
	public List<Board> getList(){
		return boardService.getList();
	}
	/*
	 * 상세 정보 리턴
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/{boardSeq}")
	public Board get(@PathVariable int boardSeq) {
		return boardService.get(boardSeq);
	}
	/*
	 * 등록/수정 처리
	 * @param board
	 */
	@GetMapping("/save")
	public int save(Board parameter) {
		 boardService.save(parameter);
		 return parameter.getBoardSeq();
	}
	/*
	 * 삭제
	 * @param boardSeq
	 */
	@GetMapping("/delete/{boardSeq}")
	public boolean delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board== null) {
			return false;
		}
		boardService.delete(boardSeq);
		return true;
	}
}
