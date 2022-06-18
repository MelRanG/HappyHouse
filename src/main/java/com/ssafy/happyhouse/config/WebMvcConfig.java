package com.ssafy.happyhouse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.happyhouse.common.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	private LoginInterceptor loginInterceptor;

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
//				.excludePathPatterns("/", "/index.html", "/login.html", "/register.html")
//				.excludePathPatterns("/login/**", "/register/**", "/css/**", "/js/**", "/img/**");
//	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:5500")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS").allowedHeaders("*")
				.allowCredentials(true).maxAge(3000);
	}

}
