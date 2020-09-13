package com.ojakgyo.service;

import java.util.List;
import java.util.Map;

import com.ojakgyo.model.dto.ImageDto;

public interface ArtworkService {

	public Map<String, Object> findAll();

	public Map<String, Object> findArtworkByImageName(String imageName);
	
	public List<ImageDto> findByEmail(String email);

	public void deleteArtwork(Long id);

}