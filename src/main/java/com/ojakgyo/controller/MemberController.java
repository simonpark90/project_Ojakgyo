package com.ojakgyo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojakgyo.exception.NotFoundException;
import com.ojakgyo.model.dto.MemberDto;
import com.ojakgyo.service.MemberService;

@RequestMapping("/members")
@RestController
public class MemberController {

	private final MemberService memberService;

	private Map<String, Object> resultMap;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping() // 회원가입 완료. 별도 uri가 없기 때문에 /members 만으로 맵핑됨.
	public ResponseEntity<Map<String, Object>> signUp(@RequestBody MemberDto memberDto) {
		resultMap = new HashMap<>();

		resultMap.put("dto", Optional.ofNullable(memberService.signUp(memberDto))
				.orElseThrow(() -> new NotFoundException("회원가입 실패!")));

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	/**
	 * 회원가입 시 이메일 중복 체크 기능.
	 * 
	 * @author YOO, 09.03.20
	 * @param email
	 * @return int, if 1 == Unavailable or Account join available
	 */
	@GetMapping("/{email}/isdupl")
	public int emailDuplCheck(@PathVariable String email) {
		int result = (memberService.emailDuplCheck(email) != null) ? 1 : -1;
		return result;
	}

	/**
	 * 아이디 찾기 기능.
	 * 
	 * @author Written By Won.JS 09/03/20
	 * @param name
	 * @param phone
	 * @return email
	 */
	@GetMapping("/info/{name}/{phone}") // 아이디 찾기
	public String findId(@PathVariable String name, @PathVariable String phone) {
		System.out.println("MemberController > findId");
		return Optional.ofNullable(memberService.findEmailByNameAndPhone(name, phone)).get();
	}

}
