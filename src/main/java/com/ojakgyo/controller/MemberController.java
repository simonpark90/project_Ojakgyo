package com.ojakgyo.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	public void signUp(@RequestBody Member member) {
		Boolean isTrue = service.signUp(member);
	}
	
	/**
	 * @param  email
	 * @return int, if 1 == Unavailable or Account join available 
	 * @author YOO, 09.03.20
	 */
	@GetMapping("/member/{email}")
	public int emailDuplCheck(@PathVariable String email){
		int mae_rong = (service.emailDuplCheck(email) != null) ? 1 : -1;
		return mae_rong;
		
		//Optional.ofNullable(service.emailDuplCheck(email)).orElseThrow(exceptionSupplier)
	}
	
//	@ApiOperation(value = "회원 단건 조회", notes = "userId로 회원을 조회한다")
//    @GetMapping(value = "/user/{userId}")
//    public SingleResult<User> findUserById(@ApiParam(value = "회원ID", required = true) @PathVariable int userId)throws Exception {
//        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
//        return responseService.getSingleResult(userJpaRepo.findById(userId).orElseThrow(Exception::new));
	
 }
	

