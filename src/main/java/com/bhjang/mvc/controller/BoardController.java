package com.bhjang.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhjang.mvc.domain.Board;
import com.bhjang.mvc.parameter.BoardParameter;
import com.bhjang.mvc.repository.BoardRepository;
import com.bhjang.mvc.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/*
 * 게시판  컨트롤러
 * @author 장보현
 */
@RestController
@RequestMapping("/board")
@Api(tags = "게시판 API")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/*
	 * 목록 리턴
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "전체조회", notes = "게시판의 전체 조회를 할수 있음")
	public List<Board> getList(){
		return boardService.getList();
	}
	/*
	 * 상세 정보 리턴
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/{boardSeq}")
	@ApiOperation(value = "상세조회", notes = "게시판 번호에 해당하는  상세 정보를 조회를 할수 있음")
	@ApiImplicitParams({
		@ApiImplicitParam(name="boardSeq" ,value="게시물 번호",example = "1")
	})
	public Board get(@PathVariable int boardSeq) {
		return boardService.get(boardSeq);
	}
	/*
	 * 등록/수정 처리
	 * @param board
	 */
	@PutMapping
	@ApiOperation(value = "등록/수정 처리", notes = "신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다")
	@ApiImplicitParams({
		@ApiImplicitParam(name="boardSeq" ,value="게시물 번호",example = "1"),
		@ApiImplicitParam(name="title" ,value="제목",example = "제목입니다"),
		@ApiImplicitParam(name="content" ,value="내용",example = "내용입니다")
	})
	public int save(BoardParameter parameter) {
		 boardService.save(parameter);
		 return parameter.getBoardSeq();
	}
	/*
	 * 삭제
	 * @param boardSeq
	 */
	@DeleteMapping("/{boardSeq}")
	@ApiOperation(value = "삭제 처리", notes = "해당 게시판 정보를 삭제합니다")
	@ApiImplicitParams({
		@ApiImplicitParam(name="boardSeq" ,value="게시물 번호",example = "1")
	})
	public boolean delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board== null) {
			return false;
		}
		boardService.delete(boardSeq);
		return true;
	}
}
