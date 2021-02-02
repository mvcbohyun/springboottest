package com.bhjang.mvc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bhjang.mvc.domain.Board;

/*
 * 게시판 Repository
 * @author 장보현
 */
@Repository
public interface BoardRepository {

	List<Board> getList();
	
	Board get(int boardSeq);
	
    void save(Board board);
	
    void update(Board board);
	
    void delete(int boardSeq);
}
