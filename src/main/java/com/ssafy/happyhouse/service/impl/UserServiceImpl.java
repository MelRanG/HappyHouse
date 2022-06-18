package com.ssafy.happyhouse.service.impl;

import com.ssafy.happyhouse.dao.UserDao;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao dao;

    @Override
    @Transactional
    public User insert(User user) {
        return dao.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        User u = dao.findByUserId(user.getUserId());
        u.setUserPassword(user.getUserPassword());
        u.setUserName(user.getUserName());
        u.setUserEmail(user.getUserEmail());
        u.setUserPhone(user.getUserPhone());
        u.setUserRoleId(user.getUserRoleId());
        return dao.save(u);
    }

    @Override
    @Transactional
    public boolean delete(String userId) {
        User u = dao.findByUserId(userId);
        if (u!=null) {
            dao.delete(u);
            return true;
        }
        else {
            return false;
        }
    }
}
