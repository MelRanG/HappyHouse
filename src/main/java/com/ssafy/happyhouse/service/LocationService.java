package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.DongDto;
import com.ssafy.happyhouse.dto.GugunDto;
import com.ssafy.happyhouse.dto.SidoDto;

public interface LocationService {

    List<SidoDto> findBySido();
    List<GugunDto> findByGugun(String sidoCode);
    List<DongDto> findByDong(String gugunCode);
}
