package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HouseInfoDto {
    //아파트명 주소 최근거래금액 최근거래일자 -> 클릭하면 모달로 세부
	private String AptName;
	private String sidoName;
	private String gugunName;
	private String dongName;
	private String jibun;
	private String buildYear;
	private int houseNo;
	private double lat;
	private double lng;
}
