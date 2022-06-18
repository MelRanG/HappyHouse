package com.ssafy.happyhouse.notice;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ssafy.happyhouse.dao.NoticeDao;
import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.service.NoticeService;
import com.ssafy.happyhouse.service.impl.NoticeServiceImpl;

public class NoticeServiceTest {
	NoticeService service;
	
	@Mock
	NoticeDao dao;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		service = new NoticeServiceImpl(dao);
	}
	
	@Test
	@DisplayName("공지 삽입")
	void insertBoards() {
		NoticeDto dto = new NoticeDto("제목", "작성자", "컨텐츠");
		
		service.insertNotice(dto);
		verify(dao).insertNotice(dto);
	}
	
	@Test
	@DisplayName("공지 수정")
	void updateBoard() {
		NoticeDto dto = new NoticeDto("제목", "작성자", "컨텐츠");
		service.updateNotice(dto);
		verify(dao).updateNotice(dto);
	}
	
	@Test
	@DisplayName("보드 삭제")
	void deleteBoard() {
		service.deleteNotice(2);
		verify(dao).deleteNotice(2);
	}

}
