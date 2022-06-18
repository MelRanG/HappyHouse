package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.service.LoginService;

@RestController
//@CrossOrigin(
//	       // localhost:5500 과 127.0.0.1 구분
//	       origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
//	       allowCredentials = "true",
//	       allowedHeaders = "*",
//	       methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
//	   )
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(value="/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDto dto, HttpSession session) {
        UserDto userDto = loginService.login(dto);
        Map<String, String> map = new HashMap<>();
        if (userDto!=null) {
            session.setAttribute("userDto", userDto);
            map.put("result", "success");
            map.put("userSeq", Integer.toString(userDto.getUserSeq()));
            map.put("userId", userDto.getUserId());
            map.put("userName", userDto.getUserName());
            map.put("userEmail", userDto.getUserEmail());
            map.put("userPhone", userDto.getUserPhone());
            if (userDto.getUserRoleId()==1){
                map.put("userRole", "관리자");
            }else if (userDto.getUserRoleId()==2){
                map.put("userRole", "일반회원");
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            map.put("result", "fail");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/logout")
    public void logout(@RequestBody UserDto userDto, HttpSession session){
        session.invalidate();
    }
}
