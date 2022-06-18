package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.BoardParamDto;
import com.ssafy.happyhouse.dto.BoardResultDto;
import com.ssafy.happyhouse.dto.RecommendDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.service.BoardService;

@RestController
//@CrossOrigin(
//	       // localhost:5500 과 127.0.0.1 구분
//	       origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
//	       allowCredentials = "true", 
//	       allowedHeaders = "*", 
//	       methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
//	   )
public class BoardController {

	BoardService service;
	
	public BoardController(BoardService service) {
		this.service = service;
	}
	
	private static final int SUCCESS = 1;
	
	@GetMapping(value="/boards")
	public ResponseEntity<BoardResultDto> findByBoards(BoardParamDto boardParamDto){
		BoardResultDto boardResultDto = new BoardResultDto();
		
		if( boardParamDto.getSearchWord().isEmpty() ) {
			boardResultDto = service.findByBoards(boardParamDto);
		}else {
			boardResultDto = service.findBySearchBoards(boardParamDto);
		}
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/boards/{id}")
	public ResponseEntity<BoardDto> findByBoardDetail(@PathVariable int id, HttpSession session){
		String userId = ((UserDto)session.getAttribute("userDto")).getUserId();
		
		BoardDto list = service.findByBoardDetail(id, userId);
		if(list != null) {
			return new ResponseEntity<>(list, HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/boards")
	public ResponseEntity<Integer> insertBoard(@RequestBody BoardDto dto){
		int list = service.insertBoard(dto);
		if(list != 0) {
			return new ResponseEntity<>(list, HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value="/boards/{id}")
	public ResponseEntity<Integer> updateBoard(@PathVariable int id, @RequestBody BoardDto dto){
		int list = service.updateBoard(dto);
		if(list != 0) {
			return new ResponseEntity<>(list, HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value="/boards/{id}")
	public ResponseEntity<Integer> deleteBoard(@PathVariable int id){
		int list = service.deleteBoard(id);
		if(list != 0) {
			return new ResponseEntity<>(list, HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="boards/recommend")
	public ResponseEntity<RecommendDto> updateRecommendCount(@RequestBody RecommendDto dto){
		RecommendDto result = service.updateRecommendCount(dto);
		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="boards/recommend")
	public ResponseEntity<RecommendDto> selectRecommendCount(RecommendDto dto){
		RecommendDto result = service.selectRecommend(dto);
		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);			
		}else {
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}
}
