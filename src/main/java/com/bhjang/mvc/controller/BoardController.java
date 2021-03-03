package com.bhjang.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bhjang.configuration.exception.BaseException;
import com.bhjang.configuration.http.BaseResponse;
import com.bhjang.configuration.http.BaseResponseCode;
import com.bhjang.framework.web.bind.annotation.RequestConfig;
import com.bhjang.mvc.domain.Board;
import com.bhjang.mvc.domain.MenuType;
import com.bhjang.mvc.domain.MySQLPageRequest;
import com.bhjang.mvc.domain.PageRequestParameter;
import com.bhjang.mvc.parameter.BoardParameter;
import com.bhjang.mvc.parameter.BoardSearchParameter;
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
//@RestController  api 서버 일때
@Controller
@Api(tags = "게시판 API")
public class BoardController {

	@Autowired
	private BoardService boardService;
	  Logger logger = LoggerFactory.getLogger(getClass());
	
	/*
	 * 목록 리턴
	 * @return
	 */
	@GetMapping("/{menuType}")
	@ApiOperation(value = "전체조회", notes = "게시판의 전체 조회를 할수 있음")
	//public BaseResponse<List<Board>> getList(
	public String  List( @PathVariable MenuType menuType,BoardSearchParameter parameter,MySQLPageRequest pageRequest, Model model) {
		logger.info("menuType : {}", menuType);
		logger.info("pageRequest : {}", pageRequest);
		parameter.setBoardType(menuType.boardType.code());
		PageRequestParameter<BoardSearchParameter> pageRequestParameter = new PageRequestParameter<BoardSearchParameter>(pageRequest, parameter);
		List<Board> boardList = boardService.getList(pageRequestParameter);
		model.addAttribute("boardList",boardList);
		model.addAttribute("menuType",menuType);
		return "/board/list";
	}

	/*
	 * 상세 정보 리턴
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/{menuType}/{boardSeq}")
	public String detail(@PathVariable MenuType menuType,@PathVariable int boardSeq,Model model) {
		Board board = boardService.get(boardSeq);
		
		if(board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"게시물"});
		}
		model.addAttribute("board",board);
		model.addAttribute("menuType",menuType);
		return "board/detail";
	}
	/*
	 * 등록 화면
	 * @param parameter
	 * @param model
	 * 
	 */
	@GetMapping("/{menuType}/form")
	@RequestConfig(loginCheck = false)
	public String form(@PathVariable MenuType menuType,BoardParameter parameter, Model model) {
		
		model.addAttribute("parameter",parameter);
		model.addAttribute("menuType",menuType);
		return "/board/form";
	}
	
	/*
	 * 수정 화면
	 * @param parameter
	 * @param model
	 * 
	 */
	@GetMapping("/{menuType}/edit/{boardSeq}")
	@RequestConfig(loginCheck = false)
	public String edit(@PathVariable MenuType menuType,@PathVariable(required = true) int boardSeq, BoardParameter parameter, Model model) {
		Board board = boardService.get(boardSeq);
		
		if(board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"게시물"});
		}
		model.addAttribute("board",board);
		model.addAttribute("parameter",parameter);
		model.addAttribute("menuType",menuType);
		return "/board/form";
	}
	
	/*
	 * 등록/수정 처리
	 * @param board
	 */
	//@PutMapping
	@PostMapping("/{menuType}/save")
	@RequestConfig(loginCheck = false)
	@ResponseBody
	@ApiOperation(value = "등록/수정 처리", notes = "신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다")
	@ApiImplicitParams({
		@ApiImplicitParam(name="boardSeq" ,value="게시물 번호",example = "1"),
		@ApiImplicitParam(name="title" ,value="제목",example = "제목입니다"),
		@ApiImplicitParam(name="content" ,value="내용",example = "내용입니다")
	})
	public BaseResponse<Integer> save(@PathVariable MenuType menuType,BoardParameter parameter) {
		
		logger.info("menuType : {}", menuType);
		if(StringUtils.isEmpty(parameter.getTitle())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"title","제목"});
		}
		if(StringUtils.isEmpty(parameter.getContent())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"content","내용"});
		}
		parameter.setBoardType(menuType.boardType.code());
		 boardService.save(parameter);
		 return new BaseResponse<Integer>(parameter.getBoardSeq());
	}
	
//	/*
//	 * 등록/수정 처리
//	 * @param board
//	 */
//	@PutMapping
//	@RequestConfig(loginCheck = false)
//	@ApiOperation(value = "등록/수정 처리", notes = "신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name="boardSeq" ,value="게시물 번호",example = "1"),
//		@ApiImplicitParam(name="title" ,value="제목",example = "제목입니다"),
//		@ApiImplicitParam(name="content" ,value="내용",example = "내용입니다")
//	})
//	public BaseResponse<Integer> save(BoardParameter parameter) {
//		
//		if(StringUtils.isEmpty(parameter.getTitle())) {
//			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"title","제목"});
//		}
//		if(StringUtils.isEmpty(parameter.getContent())) {
//			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] {"contents","내용"});
//		}
//		 boardService.save(parameter);
//		 return new BaseResponse<Integer>(parameter.getBoardSeq());
//	}
//	
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
	@RequestConfig(loginCheck = false)
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
