package com.ojakgyo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojakgyo.exception.NotFoundException;
import com.ojakgyo.service.ArtworkService;

@RequestMapping("/artworks")
@RestController
public class ArtworkController {

	private final ArtworkService artworkService;

	private Map<String, Object> resultMap;

	public ArtworkController(ArtworkService artworkService) {
		this.artworkService = artworkService;
	}	

	/**
	 * 전체 리스트 불러오는 기능.
	 * 
	 * @author JISOO.K
	 * @param
	 */
	@GetMapping()
	public ResponseEntity<Map<String, Object>> findAll() {

		resultMap = Optional.ofNullable(artworkService.findAll())
				.orElseThrow(() -> new NotFoundException("게시물이 하나도 없음!"));

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	/**
	 * 파일이름으로 상세정보 불러오는 기능.
	 * 
	 * @author Won.js
	 * @param String imageName
	 */
	@GetMapping("/detail/{imageName}")
	public ResponseEntity<Map<String, Object>> findArtworkByImageName(@PathVariable String imageName) {

		resultMap = Optional.ofNullable(artworkService.findArtworkByImageName(imageName))
				.orElseThrow(() -> new NotFoundException("조회되지 않음"));

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	/**
	 * 게시물을 삭제하는 기능.
	 * 
	 * @author JISOO.K, SIMON.P
	 * @param Long   id
	 * @param Member member
	 */
	@DeleteMapping("/delete/{id}")
	public void deleteArtwork(@PathVariable Long id){
		artworkService.deleteArtwork(id);
	}

	/**
	 * Myprofile에서 전시한 리스트 보여주기 위해 email값으로 리스트를 찾아오는 기능.
	 * 
	 * @author SIMON.P
	 * @param email
	 */
	@GetMapping("/show/{email}")
	public ResponseEntity<Map<String, Object>> findByEmail(@PathVariable String email) {
		resultMap = new HashMap<>();
		resultMap.put("dtoList", Optional.ofNullable(artworkService.findByEmail(email))
				.orElseThrow(() -> new NotFoundException("해당 유저가 없음!")));

		return new ResponseEntity<>(resultMap, HttpStatus.OK);

	}

}