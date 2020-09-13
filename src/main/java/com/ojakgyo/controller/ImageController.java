package com.ojakgyo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ojakgyo.service.ImageService;

@RequestMapping("/images")
@RestController
public class ImageController {

	private final ImageService imageService;

	private Map<String, Object> resultMap;

	public ImageController(final ImageService imageService) {
		this.imageService = imageService;
	}

	/**
	 * 리스트 화면 출력용 전체 이미지 가져오는 기능.
	 * 
	 * @author WON, YOO
	 */
	@GetMapping()
	ResponseEntity<Map<String, Object>> findAllImage() {

		resultMap = new HashMap<>();

		resultMap.put("dtoList", imageService.findAllImage());

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	/**
	 * 키워드를 통한 검색 기능.
	 * 
	 * @param String searchKeyWord
	 * @author YOO
	 */
	@PostMapping("/search")
	ResponseEntity<Map<String, Object>> findBySearchKeyWord(@RequestParam("searchKeyWord") String searchKeyWord) {
		resultMap =new HashMap<>();
		
		resultMap.put("dtoList", Optional.ofNullable(imageService.findBySearchKeyWord(searchKeyWord)));

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	/**
	 * category를 통한 검색 기능.
	 * 
	 * @param String keyword
	 * @author YOO
	 */
	@PostMapping("/category")
	ResponseEntity<Map<String, Object>> findByCategory(@RequestParam("keyword") String keyword) {
		resultMap = new HashMap<>();

		resultMap.put("dtoList", Optional.ofNullable(imageService.findByCategory(keyword)));

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

}
