package com.ssafy.happyhouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.LocationDao;
import com.ssafy.happyhouse.dto.DongDto;
import com.ssafy.happyhouse.dto.GugunDto;
import com.ssafy.happyhouse.dto.SidoDto;
import com.ssafy.happyhouse.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{

	@Autowired
	LocationDao dao;

	@Override
	@Transactional
	public List<SidoDto> findBySido() {
		// TODO Auto-generated method stub
		return dao.findBySido();
	}

	@Override
	@Transactional
	public List<GugunDto> findByGugun(String sidoCode) {
		// TODO Auto-generated method stub
		return dao.findByGugun(sidoCode);
	}

	@Override
	@Transactional
	public List<DongDto> findByDong(String GugunCode) {
		// TODO Auto-generated method stub
		return dao.findByDong(GugunCode);
	}
}
