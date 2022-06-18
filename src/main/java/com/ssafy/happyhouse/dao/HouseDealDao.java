package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.dto.HouseDealDto;

@Mapper
public interface HouseDealDao {
	List<HouseDealDto> findByHouseDeals(@Param("dong") String dong, @Param("AptName") String AptName);
}
