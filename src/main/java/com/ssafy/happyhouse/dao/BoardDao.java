package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.BoardParamDto;
import com.ssafy.happyhouse.dto.RecommendDto;

@Mapper
public interface BoardDao {
	List<BoardDto> findByBoards(BoardParamDto dto);
	List<BoardDto> findBySearchBoards(BoardParamDto dto);
	int findByBoardsTotalCount();
	int findBySearchBoardsCount(BoardParamDto dto);
	BoardDto findByBoardDetail(int id);
	int insertBoard(BoardDto dto);
	int updateBoard(BoardDto dto);
	int deleteBoard(int id);
	
	//좋아요 관련 기능
	int updateBoardRecommend(int id);
	int updateBoardNotRecommend(int id);
	int selectRecommend(RecommendDto dto);
	int insertRecommend(RecommendDto dto);
	int deleteRecommend(RecommendDto dto);
	
}
