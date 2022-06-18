package com.ssafy.happyhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.HouseDealDao;
import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.service.HouseDealService;

@Service
public class HouseDealServiceImpl implements HouseDealService{

	@Autowired
	HouseDealDao dao;
	
	@Override
	@Transactional
	public List<HouseDealDto> findByHouseDeals(String dong, String AptName) {
		// TODO Auto-generated method stub
		return dao.findByHouseDeals(dong, AptName);
	}

//	@Override
//	@Transactional
//	public List<HouseDealDto> findByHouseDeal(String aptName) {
//		// TODO Auto-generated method stub
//		List<HouseDealDto> result = dao.findByHouseDeal(aptName);
//		System.out.println(result.get(0));
//		return result;
//	}

}
