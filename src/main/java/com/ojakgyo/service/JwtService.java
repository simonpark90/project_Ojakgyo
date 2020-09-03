package com.ojakgyo.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ojakgyo.model.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {
	@Value("${jwt.salt}")//spring에서 application.properties의 값을 읽어와주는 @
	private String salt;//보안을 위해 설정된 특정 알고리즘 application.properties의 설정정보를,
	//key를 기반으로 보유하고 있는 변수
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	/**
	 * 로그인 성공시 사용자 정보를 기반으로 JWTToken을 생성해서 반환
	 * @param user
	 * @return
	 */
	public String create(final Member member) {
		if(member != null) {
			System.out.println("JwtService의 create()----");
			log.trace("time : {} ", expireMin);
			
			final JwtBuilder builder = Jwts.builder();
			
			builder.setHeaderParam("typ", "JWT");//header정보 설정.
			
			//토큰 제목설정, 유효기간, 담고 싶은 정보
			builder.setSubject("로그인 토큰").setExpiration(new Date(System.currentTimeMillis() + 100*60*expireMin)).
			claim("Member", member).claim("second", "new data");
			
			//signature - secret key를 이용한 암호화
			builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
			//oAuth와 다르게 별도의 저장소에 저장하지 않고, 토큰 그 자체가 정보가 됨.
			//마지막 직렬화 처리 - 네트웤상에서 데이터 손실없이 전송하는 구조로 구성.
			final String jwt = builder.compact();
			log.debug("토큰 발행 : {} ", jwt);
			return jwt;			
		}else {
			return null;
		}
		
	}
	
	/**
	 * 전달받은 토큰이 제대로 생성된 것인지 여부 확인
	 * 문제 발생시 RuntimeException 발생
	 * @param jwt
	 */
	public void checkValid(String jwt) {
		log.info("토큰 검증 : {} ", jwt);
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	}
	
	/**
	 * jwt 토큰을 분석 후에 필요한 정보 반환
	 * 토큰 유효성 검증도 동반됨. -> .parseClaimsJws(jwt)
	 * @param jwt
	 * @return
	 */
	public Map<String, Object> get(String jwt){
		Jws<Claims> claims = null;
		
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
			
		}catch(final Exception e) {
			throw new RuntimeException();
		}
		log.trace("claims : {} ", claims);
		log.info("----get() : " + claims.getBody().toString());//{sub=로그인 토큰, exp=1598853063, User={email=a@b.com, password=11}, second=new data}가 출력됨.
		//즉 header와 sign을 제외한 payload만 출력됨.
		//Claims은 Map의 구현체
		return claims.getBody();
	}
}
