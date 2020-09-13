package com.ojakgyo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ojakgyo.config.FileStorageProperties;
import com.ojakgyo.interceptor.JwtInterceptor;

@Configuration
@EnableConfigurationProperties(FileStorageProperties.class)
@SpringBootApplication
public class ProjectOjakgyoApplication implements WebMvcConfigurer {

	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("artworks/delete")
				.excludePathPatterns("/artworks/").excludePathPatterns("/members/**")
				.excludePathPatterns("/images/**").excludePathPatterns(Arrays.asList("/css/**"))
				.excludePathPatterns(Arrays.asList("/scripts/**")).excludePathPatterns(Arrays.asList("/plugin/**"));
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*")
				.exposedHeaders("jwt-auth-token");
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectOjakgyoApplication.class, args);
	}

}
