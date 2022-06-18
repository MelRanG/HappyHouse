package com.ssafy.happyhouse.dao;

import com.ssafy.happyhouse.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface UserDao extends JpaRepository<User, Integer>{
	User findByUserId(String userId);
}
