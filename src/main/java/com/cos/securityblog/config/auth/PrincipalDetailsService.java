package com.cos.securityblog.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.securityblog.domain.user.User;
import com.cos.securityblog.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("PrincipalDetailsService : /login이 호출되면 자동 실행되어 username이 DB에 있는지 확인함");
		User principal = userRepository.findByUsername(username);
		if(principal == null) {
			//return null; // null을 반환하면 심각한 에러가 발생한다.
			throw new UsernameNotFoundException(username); // 진짜로 이 오류를 던지는건 아닌거같음. 			
		}else {
			return new PrincipalDetails(principal); // PrincipalDetails 는 UserDetails 를 상속받고있음.
		}
	}

}
