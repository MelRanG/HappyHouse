package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.BookmarkDto;
import com.ssafy.happyhouse.dto.BookmarkResultDto;
import com.ssafy.happyhouse.service.BookmarkSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookmarkController {

    @Autowired
    BookmarkSerivce service;

    @GetMapping(value="/bookmarks/{userSeq}")
    private ResponseEntity<List<BookmarkResultDto>> bookmarkList(@PathVariable int userSeq) {
        List<BookmarkResultDto> list = service.bookmarkList(userSeq);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping(value="/bookmarks")
    private ResponseEntity<Integer> bookmarkDelete(@RequestParam int bookmarkId){
        int res = service.bookmarkDelete(bookmarkId);
        if (res==1) return new ResponseEntity<>(res, HttpStatus.OK);
        else return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @DeleteMapping(value="/bookmarks/{bookmarkId}")
//    private ResponseEntity<Integer> bookmarkDelete(@PathVariable int bookmarkId){
//        int res = service.bookmarkDelete(bookmarkId);
//        if (res==1) return new ResponseEntity<>(res, HttpStatus.OK);
//        else return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @PostMapping(value="/bookmarks")
    private ResponseEntity<Integer> bookmarkInsert(@RequestBody BookmarkDto bookmarkDto){
        int res = service.bookmarkInsert(bookmarkDto);
        if (res==1) return new ResponseEntity<>(res, HttpStatus.OK);
        else return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
