package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.DongDto;
import com.ssafy.happyhouse.dto.GugunDto;
import com.ssafy.happyhouse.dto.SidoDto;

@Mapper
public interface LocationDao {
    List<SidoDto> findBySido();
    List<GugunDto> findByGugun(String sidoCode);
    List<DongDto> findByDong(String gugunCode);
}
