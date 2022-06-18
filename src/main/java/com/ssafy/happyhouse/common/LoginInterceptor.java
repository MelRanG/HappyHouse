package com.ssafy.happyhouse.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ssafy.happyhouse.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String async = request.getHeader("async");
        HttpSession session = request.getSession();
        if(request.getMethod().contentEquals("OPTIONS")) return true;
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        if (userDto==null) {
            Gson gson = new Gson();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("result", "login");
            String jsonStr = gson.toJson(jsonObject);
            response.getWriter().write(jsonStr);
            return false;
        }
        return true;
    }
}
