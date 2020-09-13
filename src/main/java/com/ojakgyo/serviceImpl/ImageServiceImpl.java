package com.ojakgyo.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.ojakgyo.model.dto.ArtworkDto;
import com.ojakgyo.model.dto.ImageDto;
import com.ojakgyo.model.entity.Artwork;
import com.ojakgyo.model.entity.Image;
import com.ojakgyo.model.entity.Member;
import com.ojakgyo.repository.ArtworkRepository;
import com.ojakgyo.repository.ImageRepository;
import com.ojakgyo.repository.MemberRepository;
import com.ojakgyo.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	private final ImageRepository imageRepository;

	private final MemberRepository memberRepository;

	private final ArtworkRepository artworkRepository;

	private ModelMapper modelMapper;

	public ImageServiceImpl(ImageRepository imageRepository, MemberRepository memberRepository,
			ArtworkRepository artworkRepository, ModelMapper modelMapper) {
		this.imageRepository = imageRepository;
		this.memberRepository = memberRepository;
		this.artworkRepository = artworkRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ImageDto> findAllImage() {
		List<Image> images = imageRepository.findAll();

		return modelMapper.map(images, new TypeToken<List<ImageDto>>() {}.getType());
	}

	@Override
	   public List<ImageDto> findBySearchKeyWord(String searchKeyWord) {

	      List<Artwork> list = new ArrayList<>();

	      Member member = memberRepository.findMember(searchKeyWord);
	      if(member != null) list.addAll(artworkRepository.findArtworkByMemberId(member.getId()));   
	      
	      list.addAll(artworkRepository.findBySearchKeyWord(searchKeyWord));
	      
	      List<ArtworkDto> listDto = modelMapper.map(list, new TypeToken<List<ArtworkDto>>() {}.getType());
	      	      
	      //중복 artwork 제거.
	      HashSet<ArtworkDto> hashMan = new HashSet<ArtworkDto>(listDto);
	      
	      List<ArtworkDto> listNoDupl = new ArrayList<>(hashMan);
	      
	      // 리스트의 각각의 id값으로 이미지 찾고, 각 첫번째 이미지를 대표이미지로해서 이미지객체 리스트 반환.
	      int size = listNoDupl.size();
	      
	      List<ImageDto> imageDtoList = new ArrayList<>();

	      for (int i = 0; i < size; i++) {
	         imageDtoList.add(modelMapper.map(imageRepository.findById(listNoDupl.get(i).getId()).get(), ImageDto.class));
	      }
	      
	      return imageDtoList;
	   }

	@Override
	public List<ImageDto> findByCategory(String keyword) {
		return modelMapper.map(imageRepository.findByImageNameContaining(keyword), new TypeToken<List<ImageDto>>() {}.getType());
	}

}
