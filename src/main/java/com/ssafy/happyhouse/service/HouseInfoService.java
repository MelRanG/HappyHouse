package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseInfoDto;

public interface HouseInfoService {
	List<HouseInfoDto> findByAddress(String dong);
	List<HouseInfoDto> findByAptName(String aptName);
}
