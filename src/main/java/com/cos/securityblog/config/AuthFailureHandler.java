package com.cos.securityblog.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	String errorCode = "";  // 에러 코드
    String errorMsg = "";   // 에러 메세지
    
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		
		if(exception instanceof BadCredentialsException){           // 비밀번호가 일치하지 않을 때 던지는 예외
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
	        StringBuffer sb = new StringBuffer();	       
	        sb.append("<script>");
	        sb.append("alert");
	        sb.append("('계정이 없거나 비밀번호가 틀렸습니다.');");
	        sb.append("history.go(-1);");
	        sb.append("</script>");
	        
	        out.print(sb);
	        out.flush();
	        
        }
		// 스프링의 보안이 강력해서 '계정정보가 없다' 라는 정보도 주지않기 위해 모든 오류를 BadCridential 로 발생시키네요 ?? (저의 구글링 후 생각)
//		else if(exception instanceof UsernameNotFoundException){         // 계정정보가 없을때
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			
//	        StringBuffer sb = new StringBuffer();
//	        
//	        sb.append("<script>");
//	        sb.append("alert");
//	        sb.append("('없는 회원정보 입니다.');");
//	        sb.append("history.go(-1);");
//	        sb.append("</script>");
//	        
//	        out.print(sb);
//	        out.flush();
//        }
	}

}
