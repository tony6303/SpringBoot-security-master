package com.cos.securityblog.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.securityblog.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private final OAuth2DetailsService oAuth2DetailsService;
	
	@Autowired
    AuthFailureHandler authFailureHandler;
    
    @Autowired
    AuthSuccessHandler authSuccessHandler;
	
	@Bean // ioc등록만하면 알아서해줌
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // 안하면 form 태그 사용 불가능
		http.authorizeRequests()
			.antMatchers("/user/**", "/post/**" , "/reply/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") //.authenticated() // 인증이 필요한 페이지들
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // ROLE_ << 강제성있음
			.anyRequest().permitAll()
		.and()
			.formLogin()
			.loginPage("/loginForm") // x-www-url-encoded
			
		// 어렵다 !!!
			.loginProcessingUrl("/login")  // /login 주소 요청이 들어오면 시큐리티가 낚아챈다.
			.defaultSuccessUrl("/") // 로그인이 성공하면 어디로 보낼지.
			.failureHandler(authFailureHandler) // 로그인 실패 시 진행될 Handler
            .successHandler(authSuccessHandler) // 로그인 성공 시 진행될 Handler
		.and()
			.oauth2Login()  
			.userInfoEndpoint()  // 윗줄과 이 줄은 기본문법
			.userService(oAuth2DetailsService);
			
		// 원래 가려던 페이지가 인증에 막혔을때, 인증을 성공하면 원래 가려던 곳으로 보내게 해줌
		
//		.successHandler(new AuthenticationSuccessHandler() {
//			
//			@Override
//			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//					Authentication authentication) throws IOException, ServletException {
//				response.sendRedirect("/"); 
//				
//			}
//		});
			
	}

}
