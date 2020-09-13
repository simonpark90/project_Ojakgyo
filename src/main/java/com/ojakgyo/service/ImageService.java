package com.ojakgyo.service;

import java.util.List;

import com.ojakgyo.model.dto.ImageDto;

public interface ImageService {

	List<ImageDto> findAllImage();

	List<ImageDto> findBySearchKeyWord(String searchKeyWord);
	
	List<ImageDto> findByCategory(String keyword);
}
