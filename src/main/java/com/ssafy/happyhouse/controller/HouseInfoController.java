package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.service.HouseInfoService;

@RestController
@RequestMapping("/houseInfos")
public class HouseInfoController {

	@Autowired
	HouseInfoService service;
	
	@GetMapping(value="/address/{dong}")
	public ResponseEntity<List<HouseInfoDto>> findByAddress(@PathVariable String dong){
		
		List<HouseInfoDto> result = service.findByAddress(dong);

		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/aptName/{aptName}")
	public ResponseEntity<List<HouseInfoDto>> findByAptName(@PathVariable String aptName){
		List<HouseInfoDto> result = service.findByAptName(aptName);
		System.out.println(aptName);
		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}
}
