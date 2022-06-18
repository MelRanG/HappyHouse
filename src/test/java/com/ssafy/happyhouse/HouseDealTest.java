package com.ssafy.happyhouse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.service.HouseDealService;

@SpringBootTest
public class HouseDealTest {

	@Autowired
	private HouseDealService service;
	
	@Test
	public void HouseDeals조회() {
		try {
			List<HouseDealDto> list = service.findByHouseDeals("효제동", "포레스트힐시티");
			for (HouseDealDto houseDealDto : list) {
				System.out.println(houseDealDto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
