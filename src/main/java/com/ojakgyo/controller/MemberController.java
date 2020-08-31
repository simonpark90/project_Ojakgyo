package com.ojakgyo.controller;

import java.awt.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ojakgyo.model.Member;
import com.ojakgyo.service.MemberService;

@RestController
public class MemberController {
	private final MemberService service;
	
	MemberController(MemberService service){
		this.service = service;
	}
	
//	@GetMapping("/member")
//	Member findMember() {
//		
//	}
	
	@PostMapping("/member")
	void signUp(@RequestBody Member member){
		Boolean isTure = service.signUp(member);
	}

}
