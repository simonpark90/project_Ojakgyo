package com.ojakgyo.service;

import org.springframework.web.multipart.MultipartFile;

import com.ojakgyo.model.dto.ArtworkDto;
import com.ojakgyo.model.dto.ImageDto;

public interface FileStorageService {

	//파일을 경로에 저장.
	String storeFile(MultipartFile file);

	//전시물 쓰기 및 이미지 업로드
	ImageDto uploadArtwork(String email, ArtworkDto artworkDto, ImageDto imageDto);
}
