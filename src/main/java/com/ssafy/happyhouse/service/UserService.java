package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.entity.User;

public interface UserService {
    User insert(User user);
    User update(User user);
    boolean delete(String userId);
}
