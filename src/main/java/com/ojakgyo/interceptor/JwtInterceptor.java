package com.ojakgyo.interceptor;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import com.ojakgyo.service.JwtService;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtService jwtService;

	/**
	 * addInterceptor(jwtInterceptor).addPathPatterns("/api/**").
		excludePathPatterns(Arrays.asList("/api/user/**"));
		
	 */
	// 로그인 후 정보 확인 버튼 클릭시에만 호출되는 메소드
	// 로그인 후엔 jwt-auth-token이 존재 따라서 검증, 없을 경우 예외 발생
	/**
	 * preHandle() : controller로 보내기 전에 처리 Object - 핸들러 정보 jwt-auth-token 없을 경우 생성,
	 * 존재할 경우 유효성 검증
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
//		System.out.println(req.getContentType());
//		System.out.println(req.getLocalAddr());
//		System.out.println(req.getIntHeader("jwt-auth-token"));
//		System.out.println(req.getIntHeader("Access-Control-Request-Headers"));
//		System.out.println(req.getIntHeader("content-type"));
//		System.out.println(req.getProtocol());
//		System.out.println(req.getLocalPort());
//		System.out.println(req.getRequestURL());
//		System.out.println(req.getUserPrincipal());
//		System.out.println("~ ~ ~ ~ ~ ~ ~ ~~ ~ ~ ~ ~ ~ ");
//		System.out.println(req.getRemoteAddr());
//		System.out.println(req.getRemoteHost());
//		System.out.println(req.getRemotePort());
//		System.out.println(req.getRemoteUser());
		final String token = req.getHeader("jwt-auth-token");
		log.info("JwtInterceptor > preHandle > token: " + token);
		System.out.println("난: " + token);
		if (StringUtils.equals(req.getMethod(), "OPTIONS")) {
			log.info("if request options method is options, return true");
			return true;
		}
		
		System.out.println("요청 방식, url " + req.getMethod() + " : " + req.getServletPath());
		
		// request의 parameter에서 auth_token으로 넘어온 녀석을 검색
		System.out.println(" == JwtInterceptor > prehandle() : " + req.getHeader("jwt-auth-token"));
				
//		Optional.ofNullable(jwtService.checkValid(token)).orElseThrow(() -> new JwtException("토큰 없음"));
		
		try {
			jwtService.checkValid(token);
		} catch (final JwtException jwt) {
			throw new JwtException("인증 토큰이 없음!");
		} catch(final IllegalArgumentException illegal) {
			throw new IllegalArgumentException("잘못된 인자값");
		}
		
		return true;
		//of(x)는 null이 아님이 확실할 때만 사용 
		
//		if (token != null && token.length() > 0) {
//			// 유효한 토큰이면 진행, 아니면 예외 발생
//			log.info("토큰 사용 가능 : {} ", token);
//			jwtService.checkValid(token);
//			return true; // 컨트롤러 요청
//		} else {
//			System.out.println("인증 토큰이 없을때----");
//			throw new RuntimeException("인증 토큰이 없음");
//		}
		
		

	}

}
