package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.service.HouseDealService;

@RestController
public class HouseDealController {

	@Autowired
	HouseDealService service;
	
	@GetMapping(value="/houseDeal")
	public ResponseEntity<List<HouseDealDto>> findByHouseDeals(@RequestParam String dong, @RequestParam String aptName){
		System.out.println(dong + aptName);
		List<HouseDealDto> list = service.findByHouseDeals(dong, aptName);
		System.out.println("house list: "+list);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
