package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookmarkResultDto {
    private int bookmarkId;
    private int houseNo;
    private String AptName;
    private String sidoName;
    private String gugunName;
    private String dongName;
    private String jibun;
    private String buildYear;
    private double lat;
    private double lng;
    private String dong;
}
