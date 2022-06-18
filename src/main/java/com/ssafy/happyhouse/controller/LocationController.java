package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.DongDto;
import com.ssafy.happyhouse.dto.GugunDto;
import com.ssafy.happyhouse.dto.SidoDto;
import com.ssafy.happyhouse.service.LocationService;

@RestController
public class LocationController {

	@Autowired
	LocationService service;
	
	
	@GetMapping(value="/sido")
	public ResponseEntity<List<SidoDto>> findBySido(){
		List<SidoDto> list = service.findBySido();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="/gugun/{sidoCode}")
	public ResponseEntity<List<GugunDto>> findByGugun(@PathVariable String sidoCode){
		List<GugunDto> list = service.findByGugun(sidoCode);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="/dong/{gugunCode}")
	public ResponseEntity<List<DongDto>> findByDong(@PathVariable String gugunCode){
		List<DongDto> list = service.findByDong(gugunCode);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
