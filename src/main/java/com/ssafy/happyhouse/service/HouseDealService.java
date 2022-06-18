package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseDealDto;

public interface HouseDealService {

	List<HouseDealDto> findByHouseDeals(String dong, String AptName);
	//List<HouseDealDto> findByHouseDeal(String aptName);
}
