package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.dto.NoticeParamDto;
import com.ssafy.happyhouse.dto.NoticeResultDto;

public interface NoticeService {
	NoticeResultDto findByNotices(NoticeParamDto noticeParamDto);
	NoticeResultDto findBySearchNotices(NoticeParamDto noticeParamDto);
	NoticeDto findByNoticeDetail(int id);
	NoticeResultDto insertNotice(NoticeDto dto);
	NoticeResultDto updateNotice(NoticeDto dto);
	NoticeResultDto deleteNotice(int id);
}
