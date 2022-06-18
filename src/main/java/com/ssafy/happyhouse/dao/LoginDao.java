package com.ssafy.happyhouse.dao;

import com.ssafy.happyhouse.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {
    public UserDto login(String userId);
}
