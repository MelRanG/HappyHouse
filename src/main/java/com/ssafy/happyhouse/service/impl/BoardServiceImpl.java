package com.ssafy.happyhouse.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.BoardDao;
import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.BoardParamDto;
import com.ssafy.happyhouse.dto.BoardResultDto;
import com.ssafy.happyhouse.dto.RecommendDto;
import com.ssafy.happyhouse.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{

	BoardDao dao;
	
	public BoardServiceImpl(BoardDao dao) {
		this.dao = dao;
	}
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	public BoardResultDto findByBoards(BoardParamDto boardParamDto){
		BoardResultDto result = new BoardResultDto();
		System.out.println(boardParamDto);
		try {
			List<BoardDto> list = dao.findByBoards(boardParamDto);
			int count = dao.findByBoardsTotalCount();
			result.setList(list);
			result.setCount(count);
			result.setResult(SUCCESS);
			
			System.out.println(list);
		}catch(Exception e) {
			e.printStackTrace();
			result.setResult(FAIL);
		}
		return result;
	}
	
	@Override
		public BoardResultDto findBySearchBoards(BoardParamDto boardParamDto) {
		
		BoardResultDto result = new BoardResultDto();
		
		try {
			List<BoardDto> list = dao.findBySearchBoards(boardParamDto);
			int count = dao.findBySearchBoardsCount(boardParamDto);
			
			result.setList(list);
			result.setCount(count);
			result.setResult(SUCCESS);
		
		}catch(Exception e) {
			e.printStackTrace();
			result.setResult(FAIL);
		}
		
		return result;
	}
	
	@Override
	public BoardDto findByBoardDetail(int id, String userId) {
		BoardDto dto = dao.findByBoardDetail(id);
		if(dto.getWriter().equals(userId)) {
			dto.setSameUser(true);
		}
		return dto;
	}

	@Override
	public int insertBoard(BoardDto dto) {
		return dao.insertBoard(dto);
	}

	@Override
	public int updateBoard(BoardDto dto) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = dao.updateBoard(dto);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteBoard(int id) {
		int result = 0;
		try {
			result = dao.deleteBoard(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public RecommendDto updateRecommendCount(RecommendDto dto) {
		int count = dao.selectRecommend(dto);
		try {
		if(count == 0) {
			dao.updateBoardRecommend(dto.getBoardId());
			dao.insertRecommend(dto);
			dto.setResult(1);
		}else {
			dao.updateBoardNotRecommend(dto.getBoardId());
			dao.deleteRecommend(dto);
			dto.setResult(0);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public RecommendDto selectRecommend(RecommendDto dto) {
		int result = dao.selectRecommend(dto);
		if(result == 0) {
			dto.setResult(0);
		}else {
			dto.setResult(1);
		}
		return dto;
	}
	
}
