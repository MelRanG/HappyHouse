package com.ssafy.happyhouse.service.impl;

import com.ssafy.happyhouse.dao.BookmarkDao;
import com.ssafy.happyhouse.dto.BookmarkDto;
import com.ssafy.happyhouse.dto.BookmarkResultDto;
import com.ssafy.happyhouse.service.BookmarkSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkSerivce {
    @Autowired
    BookmarkDao dao;

    @Override
    public int bookmarkInsert(BookmarkDto bookmarkDto) {
        return dao.bookmarkInsert(bookmarkDto);
    }

    @Override
    public int bookmarkDelete(int bookmarkId) {
        return dao.bookmarkDelete(bookmarkId);
    }

    @Override
    public List<BookmarkResultDto> bookmarkList(int userSeq) {
        return dao.bookmarkList(userSeq);
    }

}
