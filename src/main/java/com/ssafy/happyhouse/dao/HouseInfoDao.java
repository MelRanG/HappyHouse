package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.HouseInfoDto;

@Mapper
public interface HouseInfoDao {
	List<HouseInfoDto> findByAddress(String dong);
	List<HouseInfoDto> findByAptName(String aptName);
}
