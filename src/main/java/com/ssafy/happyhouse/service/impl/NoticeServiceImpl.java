package com.ssafy.happyhouse.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.NoticeDao;
import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.dto.NoticeParamDto;
import com.ssafy.happyhouse.dto.NoticeResultDto;
import com.ssafy.happyhouse.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	NoticeDao dao;

	public NoticeServiceImpl(NoticeDao dao) {
		this.dao = dao;
	}

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
	public NoticeResultDto findByNotices(NoticeParamDto noticeParamDto) {
		NoticeResultDto result = new NoticeResultDto();
		System.out.println(noticeParamDto);
		try {
			List<NoticeDto> list = dao.findByNotices(noticeParamDto);
			int count = dao.findByNoticesTotalCount();
			result.setList(list);
			result.setCount(count);
			result.setResult(SUCCESS);

			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(FAIL);
		}
		return result;
	}

	@Override
	public NoticeResultDto findBySearchNotices(NoticeParamDto noticeParamDto) {
		NoticeResultDto result = new NoticeResultDto();

		try {
			List<NoticeDto> list = dao.findBySearchNotices(noticeParamDto);
			int count = dao.findBySearchNoticesCount(noticeParamDto);

			result.setList(list);
			result.setCount(count);
			result.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(FAIL);
		}

		return result;
	}

	@Override
	public NoticeDto findByNoticeDetail(int id) {
		NoticeDto dto = dao.findByNoticeDetail(id);
		return dto;
	}

	@Override
	public NoticeResultDto insertNotice(NoticeDto dto) {
		NoticeResultDto result = new NoticeResultDto();
		try {
			dao.insertNotice(dto);
			result.setResult(SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			result.setResult(FAIL);
		}
		return result;
	}

	@Override
	public NoticeResultDto updateNotice(NoticeDto dto) {
		NoticeResultDto result = new NoticeResultDto();
		try {
			dao.updateNotice(dto);
			result.setResult(SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			result.setResult(FAIL);
		}
		return result;
	}

	@Override
	public NoticeResultDto deleteNotice(int id) {
		NoticeResultDto result = new NoticeResultDto();
		try {
			dao.deleteNotice(id);
			result.setResult(SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			result.setResult(FAIL);
		}
		return result;
	}
}
