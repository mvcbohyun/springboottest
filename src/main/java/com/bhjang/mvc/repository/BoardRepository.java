package com.bhjang.mvc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bhjang.mvc.domain.Board;
import com.bhjang.mvc.parameter.BoardParameter;

/*
 * 게시판 Repository
 * @author 장보현
 */
@Repository
public interface BoardRepository {

	List<Board> getList();
	
	Board get(int boardSeq);
	
    void save(BoardParameter board);
	
    void update(BoardParameter board);
	
    void delete(int boardSeq);
}
