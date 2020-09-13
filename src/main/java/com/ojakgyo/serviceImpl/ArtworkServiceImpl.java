package com.ojakgyo.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.ojakgyo.model.dto.ArtworkDto;
import com.ojakgyo.model.dto.ImageDto;
import com.ojakgyo.model.dto.MemberDto;
import com.ojakgyo.model.entity.Artwork;
import com.ojakgyo.model.entity.Image;
import com.ojakgyo.repository.ArtworkRepository;
import com.ojakgyo.service.ArtworkService;

@Service
public class ArtworkServiceImpl implements ArtworkService {

	private final ArtworkRepository artworkRepository;

	private Map<String, Object> resultMap;

	private ModelMapper modelMapper;

	public ArtworkServiceImpl(ArtworkRepository artworkRepository, ModelMapper modelMapper) {
		this.artworkRepository = artworkRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Map<String, Object> findAll() {

		resultMap = new HashMap<>();

		resultMap.put("list", artworkRepository.findAll());

		return resultMap;
	}

	@Override
	public Map<String, Object> findArtworkByImageName(String imageName) {

		resultMap = new HashMap<>();
		Artwork artwork = artworkRepository.findArtworkByImageName(imageName);

		resultMap.put("artwork", modelMapper.map(artwork, ArtworkDto.class));
		resultMap.put("member", modelMapper.map(artwork.getMember(), MemberDto.class));

		return resultMap;
	}

	@Override
	public void deleteArtwork(Long id) {
		if(artworkRepository.existsById(id)) artworkRepository.deleteById(id);
	}

	@Override
	public List<ImageDto> findByEmail(String email) {
		List<Image> images = new ArrayList<>();
		List<Artwork> artworks = artworkRepository.findByEmail(email);
		int size = artworks.size();
		
		for (int i = 0; i < size; i++) {
			images.addAll(artworks.get(i).getImages());
		}
		
		return modelMapper.map(images, new TypeToken<List<ImageDto>>() {
		}.getType());

	}

}