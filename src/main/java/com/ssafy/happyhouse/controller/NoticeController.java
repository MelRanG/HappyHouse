package com.ssafy.happyhouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.dto.NoticeParamDto;
import com.ssafy.happyhouse.dto.NoticeResultDto;
import com.ssafy.happyhouse.service.NoticeService;

@RestController
//@CrossOrigin(
//	       // localhost:5500 과 127.0.0.1 구분
//	       origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
//	       allowCredentials = "true", 
//	       allowedHeaders = "*", 
//	       methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
//	   )
public class NoticeController {

	NoticeService service;
	
	public NoticeController(NoticeService service) {
		this.service = service;
	}
	
	private static final int SUCCESS = 1;
	
	@GetMapping(value="/notices")
	public ResponseEntity<NoticeResultDto> findByNotices(NoticeParamDto noticeParamDto){
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		
		System.out.println(noticeParamDto);
		if( noticeParamDto.getSearchWord().isEmpty() ) {
			noticeResultDto = service.findByNotices(noticeParamDto);
		}else {
			noticeResultDto = service.findBySearchNotices(noticeParamDto);
		}
		if( noticeResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/notices/{id}")
	public ResponseEntity<NoticeDto> findByNoticeDetail(@PathVariable int id){
		
		NoticeDto list = service.findByNoticeDetail(id);
		if(list != null) {
			return new ResponseEntity<>(list, HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/notices")
	public ResponseEntity<NoticeResultDto> insertNotice(@RequestBody NoticeDto dto){
		NoticeResultDto noticeResultDto = service.insertNotice(dto);
		if(noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(noticeResultDto, HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(noticeResultDto, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value="/notices/{id}")
	public ResponseEntity<NoticeResultDto> updateNotice(@PathVariable int id, @RequestBody NoticeDto dto){
		NoticeResultDto noticeResultDto = service.updateNotice(dto);
		if(noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(noticeResultDto, HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(noticeResultDto, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value="/notices/{id}")
	public ResponseEntity<NoticeResultDto> deleteNotice(@PathVariable int id){
		NoticeResultDto noticeResultDto = service.deleteNotice(id);
		if(noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(noticeResultDto, HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(noticeResultDto, HttpStatus.NOT_FOUND);
		}
	}
	
}
