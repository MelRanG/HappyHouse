package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.BookmarkDto;
import com.ssafy.happyhouse.dto.BookmarkResultDto;

import java.util.List;

public interface BookmarkSerivce {
    int bookmarkInsert(BookmarkDto bookmarkDto);
    int bookmarkDelete(int bookmarkId);
    List<BookmarkResultDto> bookmarkList(int userSeq);
}
