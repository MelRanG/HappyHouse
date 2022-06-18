package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.service.UserService;

@RestController
//@CrossOrigin(
//        // localhost:5500 과 127.0.0.1 구분
//        origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
//        allowCredentials = "true",
//        allowedHeaders = "*",
//        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
//)
public class UserController {
    @Autowired
    UserService service;

    //회원등록
    @PostMapping(value="/users")
    public ResponseEntity<Map<String, String>> insert(@RequestBody User user) {
        System.out.println(user);
        User u = service.insert(user);
        System.out.println(u);
        Map<String, String> map = new HashMap<>();
        if (u!=null) {
            map.put("result", "success");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            map.put("result", "fail");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //회원정보수정
    @PutMapping(value="/users/{userId}")
        public ResponseEntity<Map<String, String>> update(@PathVariable String userId, @RequestBody User user, HttpSession session) {
        System.out.println("[UserController] update() userId: "+userId);
        System.out.println("[UserController] update() user: "+user);
        User u = service.update(user);
        Map<String, String> map = new HashMap<>();
        if (u!=null) {
            session.setAttribute("user", u);
            map.put("result", "success");
            map.put("userSeq", Integer.toString(u.getUserSeq()));
            map.put("userId", u.getUserId());
            map.put("userName", u.getUserName());
            map.put("userEmail", u.getUserEmail());
            map.put("userPhone", u.getUserPhone());
            if (u.getUserRoleId()==1){
                map.put("userRole", "관리자");
            }else if (u.getUserRoleId()==2){
                map.put("userRole", "일반회원");
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            map.put("result", "fail");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //회원탈퇴
    @DeleteMapping(value="/users/{userId}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String userId) {
        boolean res = service.delete(userId);
        Map<String, String> map = new HashMap<>();
        if (res==true) {
            map.put("result", "success");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            map.put("result", "fail");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
