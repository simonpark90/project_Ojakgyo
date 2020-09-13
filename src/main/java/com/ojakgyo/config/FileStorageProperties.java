package com.ojakgyo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

// application에서 작성한 프로퍼티를 등록해주는 클래스
@ConfigurationProperties(prefix = "file") //dependency 설치 했음
public class FileStorageProperties {
	private String uploadDir;
	
	public String getUploadDir() {
		return uploadDir;
	}
	
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
}
