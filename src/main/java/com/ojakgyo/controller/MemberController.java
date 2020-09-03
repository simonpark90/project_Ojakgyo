package com.ojakgyo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ojakgyo.model.Member;
import com.ojakgyo.service.MemberService;

import lombok.extern.slf4j.Slf4j;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@Slf4j
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
	@GetMapping("/member/findid/{name}/{phone}")	
	public ResponseEntity<Map<String, Object>> findId(@PathVariable String name,@PathVariable String phone){
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			String email = service.findEmailByNameAndPhone(name,phone);
			if(email != null) {
				System.out.println("여기까지 들어옴");
				status = HttpStatus.OK;
				resultMap.put("status", true);
				resultMap.put("email", email);
			}else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		
		}catch(RuntimeException e) {
			log.error("찾기", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	
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
