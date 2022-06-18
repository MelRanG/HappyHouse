package com.ssafy.happyhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.happyhouse.service.HouseInfoService;

@SpringBootTest
public class HouseInfoTest {

	@Autowired
	private HouseInfoService service;
	
//	@Test
//	public void HouseInfoAddress() {
//		try {
//			List<HouseSearchDto> map = service.findByAddress("사직동");
//
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void HouseInfoAptName() {
//		try {
//			List<HouseSearchDto> map = service.findByAptName("인왕산아이파크");
//
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}

}
