
package com.ssafy.happyhouse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.service.UserService;

@SpringBootTest
public class UserTest {

	@Autowired
	UserService service;
	
	@Test
	public void 유저등록() {
		User u = new User();
		u.setUserId("ssafy3");
		u.setUserPassword("ssafy2");
		u.setUserName("길동");
		u.setUserEmail("gil2@dong.com");
		u.setUserPhone("010-2323-4242");
		try {
			User result = service.insert(u);
			System.out.println(result);
		}catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
}
