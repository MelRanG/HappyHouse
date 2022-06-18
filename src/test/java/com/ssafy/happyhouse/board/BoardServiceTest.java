package com.ssafy.happyhouse.board;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ssafy.happyhouse.dao.BoardDao;
import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.RecommendDto;
import com.ssafy.happyhouse.service.BoardService;
import com.ssafy.happyhouse.service.impl.BoardServiceImpl;

public class BoardServiceTest {
	BoardService service;
	
	@Mock
	BoardDao dao;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		service = new BoardServiceImpl(dao);
	}
	
	@Test
	@DisplayName("보드 삽입")
	void insertBoards() {
		BoardDto dto = new BoardDto("제목", "작성자", "컨텐츠");
		
		service.insertBoard(dto);
		verify(dao).insertBoard(dto);
	}
	
	@Test
	@DisplayName("보드 수정")
	void updateBoard() {
		BoardDto dto = new BoardDto("제목2", "작성자2", "컨텐츠2");
		service.updateBoard(dto);
		verify(dao).updateBoard(dto);
	}
	
	@Test
	@DisplayName("보드 삭제")
	void deleteBoard() {
		service.deleteBoard(2);
		verify(dao).deleteBoard(2);
	}
	
	@Test
	@DisplayName("보드추천")
	void RecommendBoard() {
		RecommendDto dto = new RecommendDto("ssafy", 2);
		service.updateRecommendCount(dto);
		verify(dao).updateBoardRecommend(2);
	}

}
