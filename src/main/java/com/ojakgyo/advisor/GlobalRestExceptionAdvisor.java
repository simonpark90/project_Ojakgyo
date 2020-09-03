package com.ojakgyo.advisor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.JwtException;


@RestControllerAdvice//Web app 전역에 대한 Exception처리를 위한 API를 제공함, json 형태로 결과를 반환하려면 이 @를 씀. (base = "추가 패키지")를 적용하면 해당 패키지에만 예외처리를 적용시킬 수 있음.
public class GlobalRestExceptionAdvisor {
	/**
	 * Spring에서는 Controller에서 발생한 예외에 대해 Common하게 처리할 수 있는 기능을 제공한다.
	 * @ExceptionHandler를 통해 Controller의 메소드에서 throw된 Exception에 대한 공통적인 처리를 할 수 있도록 지원하고 잇음.
	 * 
	 */
	private Map<String, Object> resultMap;
	
	//@ExceptionHandler의 인자값으로 Exception.class를 지정할 경우 다른 ExceptionHandler에서 걸러지지 않은 예외가 있으면 최종적으로 이 handler를 거쳐 처리가 됨.
	@ExceptionHandler(value = {Exception.class})
	public Map<String, Object> defaultException(Exception e){
		System.out.println("GlobalRestExceptionAdvisor > defaultException()");
		resultMap = new HashMap<>();
		resultMap.put("message", e.getMessage());
		
		return resultMap;
	}
	
	@ExceptionHandler(value = { RuntimeException.class })//Exception이 발생하면 해당 Handler로 처리하겠다고 명시하는 anno, 어떤 Exception이 발생할 때 handler를 적용할 것인지에 대한 Exception class를 인자로 넣음.
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//해당 Exception이 발생하면 Response에 추력되는 HttpStatus Code가 500으로 내려가도록 설정, 참고로 성공 시에는 200으로 내려감.
	//실습에서 HttpStatus Code의 역할은 성공이냐 아니냐 정도의 의미만 잇고 실제 사용하는 성공 실패여부는 json으로 출력되는 정보를 이용함.
	public Map<String, Object> internalServerError(Exception e) {
		resultMap = new HashMap<>();
		resultMap.put("message", e.getMessage());
//		resultMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return resultMap;
	}
	
	@ExceptionHandler(value = {NullPointerException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> NullPointerException(NullPointerException e){
		resultMap = new HashMap<>();
		resultMap.put("message", e.getMessage());
		System.out.println("GlobalRestExceptionAdvisor > NullPointerException()");
		return resultMap;
	}
	
	@ExceptionHandler(value = {JwtException.class})
	public Map<String, Object> jwtException(JwtException e){
		resultMap = new HashMap<>();
		resultMap.put("message", e.getMessage());
		System.out.println("GlobalRestExceptionAdvisor > jwtException()");
		return resultMap;
	}
	
}
