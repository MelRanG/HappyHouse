package com.ssafy.happyhouse.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.HouseInfoDao;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.service.HouseInfoService;

@Service
public class HouseInfoServiceImpl implements HouseInfoService{

	@Autowired
	HouseInfoDao dao;
	
	@Override
	public List<HouseInfoDto> findByAddress(String dong) {
		// TODO Auto-generated method stub
		List<HouseInfoDto> result = new ArrayList<HouseInfoDto>();

		try {
			result = dao.findByAddress(dong);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<HouseInfoDto> findByAptName(String aptName) {
		List<HouseInfoDto> result = new ArrayList<HouseInfoDto>();
		try {
			result = dao.findByAptName(aptName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
