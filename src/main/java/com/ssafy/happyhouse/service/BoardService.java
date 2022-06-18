package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.BoardParamDto;
import com.ssafy.happyhouse.dto.BoardResultDto;
import com.ssafy.happyhouse.dto.RecommendDto;

public interface BoardService {
	BoardResultDto findByBoards(BoardParamDto boardParamDto);
	BoardResultDto findBySearchBoards(BoardParamDto boardParamDto);
	BoardDto findByBoardDetail(int id, String userId);
	int insertBoard(BoardDto dto);
	int updateBoard(BoardDto dto);
	int deleteBoard(int id);
	
	//좋아요 관련 기능
	RecommendDto updateRecommendCount(RecommendDto dto);
	RecommendDto selectRecommend(RecommendDto dto);
}
