package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeParamDto {
	private int limit;
	private int offset;
	private String searchWord;
	
	private int userSeq;
	
}
