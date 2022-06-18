package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookmarkDto {
    private int bookmarkId;
    private int userSeq;
    private int houseNo;
}
