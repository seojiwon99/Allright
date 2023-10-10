package com.ar.lighthouse.member.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//세션 정보 필요하면 적어야함
		
		CustomUser user = (CustomUser)authentication.getPrincipal();
		HttpSession session = request.getSession();
		System.out.println("=============================="+user.getMemberVo());
		session.setAttribute("loginMember", user.getMemberVo()); 
		
		response.sendRedirect("/main");
	}
	
	@Bean
	public UserDetailsService userService() {
		return new CustomUserDetailService();
	}
	
	@Bean
	public AuthenticationSuccessHandler customLoginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
}
