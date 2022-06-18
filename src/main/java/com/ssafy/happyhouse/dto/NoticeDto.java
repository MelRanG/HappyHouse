package com.ssafy.happyhouse.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class NoticeDto {
	private int id;
	@NonNull
	private String title;
	@NonNull
	private String writer;
	@NonNull
	private String content;
	private LocalDateTime regDate;

}
