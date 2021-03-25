package com.bhjang.mvc.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bhjang.mvc.domain.Board;
import com.bhjang.mvc.domain.PageRequestParameter;
import com.bhjang.mvc.parameter.BoardParameter;
import com.bhjang.mvc.parameter.BoardSearchParameter;

/*
 * 게시판 Repository
 * @author 장보현
 */
@Repository
public interface BoardRepository {

	List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter);
	
	List<Board> getListApi();
	
	Board get(int boardSeq);
	
    void save(BoardParameter board);
    
    void saveList(Map<String, Object> paramMap);
	
    void update(BoardParameter board);
	
    void delete(int boardSeq);
}
