package com.ssafy.happyhouse.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.happyhouse.dto.RecommendDto;
import com.ssafy.happyhouse.service.BoardService;

@SpringBootTest
public class BoardsServiceTotalTest {

	@Autowired
	BoardService service;
	
	@Test
	void 추천증가테스트() {
		RecommendDto recommendDto = new RecommendDto("ssafy", 2);
		service.updateRecommendCount(recommendDto);
		System.out.println(service.selectRecommend(recommendDto));
	}
}
