package com.ojakgyo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojakgyo.model.dto.MemberDto;
import com.ojakgyo.service.JwtService;
import com.ojakgyo.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/auth")
@RestController
@Slf4j
public class AuthorizationController {

	private JwtService jwtService;

	private MemberService memberService;

	public AuthorizationController(JwtService jwtService, MemberService memberService) {
		this.jwtService = jwtService;
		this.memberService = memberService;
	}

	@PostMapping()
	public ResponseEntity<Map<String, Object>> signInCheck(@RequestBody MemberDto memberDto, HttpServletRequest req,
			HttpServletResponse res) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		MemberDto loginMember = memberService.signInCheck(memberDto);
		Optional.ofNullable(loginMember).orElseThrow(() -> new RuntimeException("로그인 실패!!"));

		String token = jwtService.create(loginMember);

		res.setHeader("jwt-auth-token", token);

		resultMap.put("auth_token", token);
		resultMap.put("status", true);
		resultMap.put("data", loginMember);

		status = HttpStatus.OK;

		log.info("resultMap : " + resultMap);

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
