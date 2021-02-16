package com.bhjang.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhjang.mvc.domain.Board;
import com.bhjang.mvc.domain.PageRequestParameter;
import com.bhjang.mvc.parameter.BoardParameter;
import com.bhjang.mvc.parameter.BoardSearchParameter;
import com.bhjang.mvc.repository.BoardRepository;
/*
 * 게시판 서비스
 * @author 장보현
 */
@Service
public class BoardService {

	@Autowired
	private BoardRepository repository;
	
	/*
	 * 목록 리턴
	 * @return
	 */
	public List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter) {
		return repository.getList(pageRequestParameter);
	}
	/*
	 * 상세 정보 리턴
	 * @param boardSeq
	 * @return
	 */
	public Board get(int boardSeq) {
		return repository.get(boardSeq);
	}
	/*
	 * 등록처리
	 * @param board
	 */
	public void save(BoardParameter parameter) {
		// 조회 하여 리턴된 정보 
		Board board = repository.get(parameter.getBoardSeq());
		if(board==null) {
			repository.save(parameter);
		}else {
			repository.update(parameter);
		}
		 
		
	}
	/*
	 * 업데이트
	 * @param board
	 */
	public  void update(BoardParameter board) {
		 repository.update(board);
	}
	/*
	 * 삭제
	 * @param boardSeq
	 */
	public void delete(int boardSeq) {
		
		 repository.delete(boardSeq);
	}
	
	/*
	 * 단순 반속문을 이용한 등록 처리
	 */
	public void saveList1(List<BoardParameter>  list) {
		for(BoardParameter parameter : list) {
			repository.save(parameter);
		}
	}
	
	public void saveList2(List<BoardParameter>  boardList) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardList",boardList);
		repository.saveList(paramMap);
		
	}
}
