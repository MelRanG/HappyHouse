package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RecommendDto {
	@NonNull
	private String userId;
	@NonNull
	private int boardId;
	private int result;
}
