package com.ar.lighthouse.member.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{
    private final RequestCache requestCache = new HttpSessionRequestCache();
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//세션 정보 필요하면 적어야함
		clearSession(request);
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		//session에 유저 정보 입력
		CustomUser user = (CustomUser)authentication.getPrincipal();
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", user.getMemberVo()); 
        
        //prevPage가 존재하는 경우 = 사용자가 직접 /auth/login 경로로 로그인 요청
        // 기존 Session의 prevPage attribute 제거
       
        String prevPage = (String) request.getSession().getAttribute("prevPage");
        if (prevPage != null) {
            request.getSession().removeAttribute("prevPage");
        }
        // 기본 URI
        String uri = "/";
        //savedRequest 존재하는 경우 = 인증 권한이 없는 페이지 접근
        //Security Filter가 인터셉트하여 savedRequest에 세션 저장
        //
        if (savedRequest != null) {
            uri = savedRequest.getRedirectUrl();
        } else if (prevPage != null && !prevPage.equals("")) {
            // 회원가입 - 로그인으로 넘어온 경우 "/"로 redirect
            if (prevPage.contains("/member/join")) {
                uri = "/";
            } else if(prevPage.contains("/member/loginForm")){
            	uri = "/";
            } else {
                uri = prevPage;
            }
        }
        redirectStrategy.sendRedirect(request, response, uri);
	}
	
	@Bean
	public UserDetailsService userService() {
		return new CustomUserDetailService();
	}
	
	@Bean
	public AuthenticationSuccessHandler customLoginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	// 로그인 실패 후 성공 시 남아있는 에러 세션 제거
    protected void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
	
}
