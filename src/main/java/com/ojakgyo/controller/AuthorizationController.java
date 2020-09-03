package com.ojakgyo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojakgyo.model.Member;
import com.ojakgyo.repository.MemberRepository;
import com.ojakgyo.service.JwtService;

import lombok.extern.slf4j.Slf4j;


@RequestMapping("/api")
@RestController
@Slf4j
public class AuthorizationController {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MemberRepository repository;
	
	/* ResponseEntity(http status code를 같이 쓰기 위해서는 이 ResponseEntity클래스를 사용함)
	 * - 데이터 + http status code
	 * - 즉 리턴시 map과 상태 메세지를 함께 전송
	 * 
	 * RestController 기본 구조는 별도의 View 제공없이 데이터를 바로 응답
	 * 상황에 따라 결과 데이터와 예외적인 상황에서 문제가 발생 할 수도 있음
	 * ResponseEntity API는 개발자가 직접 결과 데이터와 HTTP 상태 코드를 직접 제어할 수 있는 클래스로 개발자는 404나 500과 같은 
	 * HTTP 상태 코드를 전송하고 싶은 데이터와 함께 전송 할 수 있는 세밀한 작업시에 사용
	 */
	//{"email":"a@b.com", "password":11} 입력하면서 실행
	
	
	
	/*
	 * interceptor 제외 로직
	 * 로그인 시 유효한 경우 JWT 생성해서 response의 header로 client에게 전송.
	 */
	@PostMapping("/logincheck/signin")
	public ResponseEntity<Map<String, Object>> signIn(@RequestBody Member member, HttpServletRequest req,HttpServletResponse res){
		log.info("--- 로그인 버튼 클릭시에 실행되는 메소드 ---");
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(req.getMethod());
		//
		try {
			Member loginMember = repository.findMemberByEmailAndPassword(member.getEmail(), member.getPassword());
			
			System.out.println(loginMember);
			//로그인 성공시 토큰 생성
			String token = jwtService.create(loginMember);
			System.out.println("======내가 토큰!!!!!!!======"+token);
			
			//토큰 정보는 response의 헤더로 보내고 나머지는 map에 담아두기
			res.setHeader("jwt-auth-token", token);
			
			System.out.println("생성된 token -- " + token);
			
			if(token != null) {
				status = HttpStatus.OK;
				resultMap.put("auth_token", token);
				resultMap.put("status", true);
				resultMap.put("data", loginMember);
			}else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		
		}catch(RuntimeException e) {
			log.error("로그인 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		log.info("**************************" + resultMap);
		
		//리턴시 map과 상태 메세지를 함께 전송
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/*
	 * http://ip/api/info api는 이 클래스의 mapping값으로 공통 적용.
	 * 요청 객체로부터 jwt토큰 정보 획득해서 검수 후 서비스 진행. 여긴 토큰 생성이 아님. 
	 */
	//JwtInterceptor의 메소드 실행 : 토큰이 있다면 검증 후 해당 메소드 실행/ 없다면 예외 발생해서 이 메소드 실행 안 함
	@PostMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req, @RequestBody Member member){
		log.info("---- getInfo () -----------------");
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			//사용자에게 전달할 정보
			
			//client로부터 전송되는 header에서 jwt-auth-token 키에 매핑된 데이터로 get 
			//토큰에 담긴 정보 전달, 서버에서 토큰을 사용하는 방법 
			System.out.println(jwtService.get(req.getHeader("jwt-auth-token")));
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			System.out.println("==info()의=====get Header========" +req.getHeader("jwt-auth-token"));
			resultMap.put("status", true);
			resultMap.put("request_body", member);
			status = HttpStatus.ACCEPTED;
			
		}catch(RuntimeException e) {
			log.error("정보 조회 실패 ", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
}






















