package com.ojakgyo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ojakgyo.interceptor.JwtInterceptor;

@Configuration
@SpringBootApplication
public class ProjectOjakgyoApplication implements WebMvcConfigurer{

	/*
	 * interceptor를 적용가능한 클래스로 변경해주기
	 * 포함 url : /api/**, /api/employee/**
	 * 제외 url : /api/logincheck/**
	 * 			정적 파일 제외 " /css/**"/ "/scripts/**", "/plugin/**"
	 */
	
	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	/**
	 * WebMvcConfigurer
	 * addinterceptors() : 전처리 후처리용 url 패턴을 적용 하기 위한 등록을 위한 API
	 * 메소드 체이닝기술로 다수의 메소드 호출 가능
	 */
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//InterceptorRegistry : 인터셉터 등록해주는 객체
		System.out.println("addInterceptors() --");
		
		//기본 적용 경로로, 적용 제외 경로, 포함시킬경로와 포함시키지 않을 경로 설정.
		
//여기수정.										//addPathPatterns("/api/**")
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**").
		excludePathPatterns("/api/logincheck/**")
		.excludePathPatterns(Arrays.asList("/css/**"))
				.excludePathPatterns(Arrays.asList("/scripts/**"))
						.excludePathPatterns(Arrays.asList("/plugin/**"));//Arrays.asList는 지워도 됨.
		
	}
	
	//CORS(Cross-Origin Resource Sharing)
	//웹 브라우저에서 외부 도메인 서버와 통신하기 위한 방식을 표준화한 스펙
	/*jwt-auth-token키로 타 도메인으로부터 요청 또는 응답을 사용하기 위한 설정.
	 * 
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println("addCorsMappings() --");
		//addMapping() : CORS를 적용할 URL패턴 
		
		
		/*
		 * Default값은 아래와 같습니다.
			Allow all origins.
			Allow "simple" methods GET, HEAD and POST.
			Allow all headers.
			Set max age to 1800 seconds (30 minutes).
		 */
		
		//allowedOrigins() : 자원 공유를 허락할 Origin지정.
		
		registry.addMapping("/**").allowedOrigins("http://localhost:8081")
		.allowedMethods("*")
		.allowedHeaders("*").exposedHeaders("jwt-auth-token");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectOjakgyoApplication.class, args);
	}

}
