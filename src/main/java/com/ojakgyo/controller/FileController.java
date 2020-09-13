package com.ojakgyo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ojakgyo.exception.NotFoundException;
import com.ojakgyo.model.dto.ArtworkDto;
import com.ojakgyo.model.dto.ImageDto;
import com.ojakgyo.service.FileStorageService;

@RestController
@RequestMapping("/files")
public class FileController {

	private final FileStorageService fileStorageService;

	private Map<String, Object> resultMap;

	public FileController(final FileStorageService fileStorageService) {
		this.fileStorageService = fileStorageService;
	}

	/**
	 * @author YOO, WON
	 * 
	 * @param String email
	 * @param co
	 * @param title
	 * @param file
	 * @return
	 */
	@PostMapping("/uploadby/{email}")
	public ResponseEntity<Map<String, Object>> uploadArtwork(@PathVariable String email,
			@RequestParam("content") String content, @RequestParam("title") String title,
			@RequestPart("file") MultipartFile file) {

		resultMap = new HashMap<>();

		String fileName = fileStorageService.storeFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/frontend")
				.path("/src/assets/upload/").path(fileName).toUriString();

		ArtworkDto artworkDto = ArtworkDto.builder().title(title).content(content).build();
		ImageDto imageDto = ImageDto.builder().imageName(fileName).imagePath(fileDownloadUri).imageSize(file.getSize())
				.imageType(file.getContentType()).build();

		resultMap.put("dto", Optional.ofNullable(fileStorageService.uploadArtwork(email, artworkDto, imageDto))
				.orElseThrow(() -> new NotFoundException("이미지 저장 실패!")));

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

}