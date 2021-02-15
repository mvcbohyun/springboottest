package com.bhjang.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhjang.configuration.exception.BaseException;
import com.bhjang.configuration.http.BaseResponse;
import com.bhjang.configuration.http.BaseResponseCode;
import com.bhjang.mvc.domain.Board;
import com.bhjang.mvc.parameter.BoardParameter;
import com.bhjang.mvc.parameter.BoardSearchParameter;
import com.bhjang.mvc.repository.BoardRepository;
import com.bhjang.mvc.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
	  Logger logger = LoggerFactory.getLogger(getClass());
	
	/*
	 * 목록 리턴
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "전체조회", notes = "게시판의 전체 조회를 할수 있음")
	public BaseResponse<List<Board>> getList(@ApiParam BoardSearchParameter parameter){
		logger.info("getList");
		return new BaseResponse<List<Board>>(boardService.getList(parameter));
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
	public BaseResponse<Board> get(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		
		if(board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"게시물"});
		}
		return new BaseResponse<Board>(boardService.get(boardSeq));
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
	public BaseResponse<Integer> save(BoardParameter parameter) {
		
		if(StringUtils.isEmpty(parameter.getTitle())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"title","제목"});
		}
		if(StringUtils.isEmpty(parameter.getContent())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"contents","내용"});
		}
		 boardService.save(parameter);
		 return new BaseResponse<Integer>(parameter.getBoardSeq());
	}
	
	/*
	 * 대용량 등록 처리1
	 */
	@PutMapping("/saveList1")
	@ApiOperation(value = "대용량 등록 처리1", notes = "대용량 등록 처리1")
	public BaseResponse<Boolean> saveList1() {
		
		int count =0;
		// 10000건의 데이터 생성
		List<BoardParameter> list = new ArrayList<BoardParameter>();
		while(true) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String content = RandomStringUtils.randomAlphabetic(10);
			list.add(new BoardParameter(title, content));
			if(count >= 10000) {
				break;
			}
		}
		// 시간 측정 
		long start = System.currentTimeMillis();
		boardService.saveList1(list);
		long end = System.currentTimeMillis();
		logger.info("실행 시간 : {}",(end -start)/1000.0);
		return new BaseResponse<Boolean>(true);
	}
	
	/*
	 * 대용량 등록 처리2  이방법이 훨씬 빠름
	 */
	@PutMapping("/saveList2")
	@ApiOperation(value = "대용량 등록 처리2", notes = "대용량 등록 처리2")
	public BaseResponse<Boolean> saveList2() {
		
		int count =0;
		// 10000건의 데이터 생성
		List<BoardParameter> list = new ArrayList<BoardParameter>();
		while(true) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String content = RandomStringUtils.randomAlphabetic(10);
			list.add(new BoardParameter(title, content));
			if(count >= 10000) {
				break;
			}
		}
		// 시간 측정 
		long start = System.currentTimeMillis();
		boardService.saveList2(list);
		long end = System.currentTimeMillis();
		logger.info("실행 시간 : {}",(end -start)/1000.0);
		return new BaseResponse<Boolean>(true);
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
	public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board== null) {
			return new BaseResponse<Boolean>(false);
		}
		boardService.delete(boardSeq);
		return new BaseResponse<Boolean>(true);
	}
	
	
}
