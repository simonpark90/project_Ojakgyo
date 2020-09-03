package com.ojakgyo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ojakgyo.model.Member;
import com.ojakgyo.service.MemberService;

//@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class MemberController {
	private final MemberService service;

	MemberController(MemberService MemberServiceImpl) {
		this.service = MemberServiceImpl;
	}

	
	
//	@ApiOperation(value = "전체 직원 조회 기능", notes = "모든 회원 정보를 List<Employee>형태로 가져옴.\n"
//			+ "예외 발생 시 : 데이터가 존재하지 않을 경우 Could not employee list!로 예외 발생.")
	
	//내가 혼자 할라햇던거
//	@PostMapping("/member/login")
//	Member findMember(String email, String password, HttpSession session) {
//		service.findByNickName(email, password);
//		
//		return null;
//	}
	
	@PostMapping("/member")//회원가입
	void signUp(@RequestBody Member member) {
		Boolean isTrue = service.signUp(member);
		//isTrue값에 따라 페이지 분기.
		
//		if (list.size() != 0)
//			return list;
//		else {
//			throw new EmployeeNotFoundException();
//		}
	}
}
