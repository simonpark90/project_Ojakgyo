package com.ojakgyo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ojakgyo.model.dto.MemberDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {
	@Value("${jwt.salt}")
	private String salt;

	@Value("${jwt.expmin}")
	private Long expireMin;

	public String create(final MemberDto loginMember) {

		log.trace("time : {} ", expireMin);

		final JwtBuilder builder = Jwts.builder();

		builder.setHeaderParam("typ", "JWT");

		builder.setSubject("로그인 토큰").setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * expireMin))
				.claim("Member", loginMember).claim("second", "new data");

		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		final String jwt = builder.compact();
		log.debug("토큰 발행 : {} ", jwt);
		return jwt;
	}

	public Jws<Claims> checkValid(String jwt) {
		log.info("토큰 검증 : {} ", jwt);
		return Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	}

}
