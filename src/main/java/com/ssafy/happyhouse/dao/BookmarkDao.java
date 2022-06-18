package com.ssafy.happyhouse.dao;

import com.ssafy.happyhouse.dto.BookmarkDto;
import com.ssafy.happyhouse.dto.BookmarkResultDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookmarkDao {
    int bookmarkInsert(BookmarkDto bookmarkDto);
    int bookmarkDelete(int bookmarkId);
    List<BookmarkResultDto> bookmarkList(int userSeq);
}
