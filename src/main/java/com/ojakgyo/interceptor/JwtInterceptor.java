package com.ojakgyo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import com.ojakgyo.service.JwtService;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

	private JwtService jwtService;
	
	public JwtInterceptor(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
		
		final String token = req.getHeader("jwt-auth-token");
		log.info("JwtInterceptor > preHandle > token : " + token);
		
		if (StringUtils.equals(req.getMethod(), "OPTIONS")) {
			log.info("if request options method is options, return true");
			return true;
		}
		
		try {
			jwtService.checkValid(token);
		} catch (final JwtException jwt) {
			throw new JwtException("인증 토큰이 없음!");
		} catch(final IllegalArgumentException illegal) {
			throw new IllegalArgumentException("잘못된 인자값");
		}
		
		return true;
	}

}
