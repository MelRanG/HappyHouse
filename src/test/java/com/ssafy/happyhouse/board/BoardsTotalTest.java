package com.ssafy.happyhouse.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.happyhouse.controller.BoardController;
import com.ssafy.happyhouse.dto.BoardDto;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardsTotalTest {

	@Autowired
	BoardController controller;

	@Autowired
	MockMvc mvc;

	@Test
	@Transactional
	@DisplayName("저장")
	void board삽입() throws Exception {
		BoardDto dto = new BoardDto("제목2", "작성자2", "컨텐츠2");
		String jsonData = new Gson().toJson(dto);

		mvc.perform(post("/boards").contentType(MediaType.APPLICATION_JSON).content(jsonData))
				.andExpect(status().isOk()); // 상태코드만 확인

		MvcResult mvcResult = mvc.perform(get("/boards")).andExpect(status().isOk()).andDo(print()).andReturn();

		String jsonResult = mvcResult.getResponse().getContentAsString();
		System.out.println("jsonResult: " + jsonResult);

		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = jsonParser.parse(jsonResult).getAsJsonArray();
		Gson gson = new Gson();
		boolean isContainDto = false;

		for (JsonElement jsonElement : jsonArray) {
			BoardDto board = gson.fromJson(jsonElement, BoardDto.class);
			if ("제목2".equals(board.getTitle()) && "작성자2".equals(board.getWriter())) {
				isContainDto = true;
				break;
			}
		}
		assertThat(isContainDto).isTrue();
	}

	@Test
	@Transactional
	@DisplayName("삭제")
	void board삭제() throws Exception {
		MvcResult mvcResult1 = mvc.perform(get("/boards")).andExpect(status().isOk()).andDo(print()).andReturn();

		String jsonResult = mvcResult1.getResponse().getContentAsString();

		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = jsonParser.parse(jsonResult).getAsJsonArray();

		int id = jsonArray.get(0).getAsJsonObject().get("id").getAsInt();
		int size = jsonArray.size();

		mvc.perform(delete("/boards/" + id)).andExpect(status().isOk());

		MvcResult mvcResult2 = mvc.perform(get("/boards")).andExpect(status().isOk()).andDo(print()).andReturn();

		String jsonResult2 = mvcResult2.getResponse().getContentAsString();
		assertThat(jsonParser.parse(jsonResult2).getAsJsonArray().size()).isEqualTo(size-1);
	}
}
