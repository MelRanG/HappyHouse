package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HouseDealDto {
	private int no;
	private String dong;
	private String AptName;
	private String dealAmount;
	private String dealYear;
	private String dealMonth;
	private String dealDay;
	private int floor;
	private double area;
}