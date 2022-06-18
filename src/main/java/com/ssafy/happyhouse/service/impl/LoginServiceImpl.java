package com.ssafy.happyhouse.service.impl;

import com.ssafy.happyhouse.dao.LoginDao;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    LoginDao loginDao;
    @Override
    @Transactional
    public UserDto login(UserDto dto) {
        UserDto userDto = loginDao.login(dto.getUserId());
        if (userDto!=null && userDto.getUserPassword().equals(dto.getUserPassword())) {
            return userDto;
        }
        else {
            return null;
        }
    }
}
