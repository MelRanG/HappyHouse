package com.ssafy.happyhouse.board;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.ssafy.happyhouse.controller.BoardController;
import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.BoardParamDto;
import com.ssafy.happyhouse.dto.BoardResultDto;
import com.ssafy.happyhouse.service.BoardService;

@WebMvcTest(BoardController.class)
public class BoardControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	BoardService service;
	
	@Test
	void 맴버리스트조회테스트() throws Exception{
		BoardParamDto paramDto = new BoardParamDto(1, 0, "");
		BoardDto boardDto = new BoardDto("제목", "작성자", "컨텐츠");
		List<BoardDto> list = new ArrayList<>();
		list.add(boardDto);
		
		BoardResultDto boardResultDto = new BoardResultDto();
		given(service.findByBoards(paramDto)).willReturn(boardResultDto);
		
		mvc.perform(get("/boards"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("제목")));
	}
	
	@Test
	void 보드삽입테스트() throws Exception{

		BoardDto dto = new BoardDto("제목", "작성자", "컨텐츠");
		Gson gson = new Gson();
		String content = gson.toJson(dto);
		
		mvc.perform(post("/boards")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content))
				.andExpect(status().isOk());
		//해당 메서드가 실행됐는지 테스트
		verify(service).insertBoard(dto);
	}
	
}
