package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.BoardParamDto;
import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.dto.NoticeParamDto;

@Mapper
public interface NoticeDao {
	List<NoticeDto> findByNotices(NoticeParamDto noticeParamDto);
	List<NoticeDto> findBySearchNotices(NoticeParamDto noticeParamDto);
	NoticeDto findByNoticeDetail(int id);
	int findByNoticesTotalCount();
	int findBySearchNoticesCount(NoticeParamDto dto);
	int insertNotice(NoticeDto dto);
	int updateNotice(NoticeDto dto);
	int deleteNotice(int id);
	

}
