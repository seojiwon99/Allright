package com.ar.lighthouse.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@EnableWebSecurity
@Configuration
public class SecurityConfig{
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
			.csrf().disable()
    		.authorizeHttpRequests((requests) -> requests	
    		.antMatchers("/", "/login/**","/common/**","/fonts/**","/startbootstrap/**", "/css/**", "/js/**", "/img/**", "/upload/**").permitAll()
            // /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
    		.antMatchers("/custom/inquiry").hasAnyAuthority("ROLE_1","ROLE_2", "ROLE_3", "ROLE_4")
    		.antMatchers("/seller/**").hasAuthority("ROLE_2")
            .antMatchers("/admin/**").hasAuthority("ROLE_4")
            .antMatchers("/page/buyer/**").hasAnyAuthority("ROLE_1", "ROLE_3")
            // 나머지 요청에 대해서는 로그인을 요구하지 않음
            .anyRequest().permitAll()
    		)
    		.formLogin((form) -> form
    				.loginPage("/page/member/loginForm")
    				.loginProcessingUrl("/login")
    				.successHandler(customLoginSuccessHandler())
    				.permitAll()
			)	
    		.logout(logout -> logout
                    // 로그아웃 요청을 처리할 URL 설정
                    .logoutUrl("/logout")
                    // 로그아웃 성공 시 리다이렉트할 URL 설정
                    .logoutSuccessUrl("/")
                    // 로그아웃 성공 핸들러 추가 (리다이렉션 처리)
                    .logoutSuccessHandler((request, response, authentication) ->{
                    		request.getSession().invalidate();
                            response.sendRedirect("/");
                            })
            );
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }
	
   @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
