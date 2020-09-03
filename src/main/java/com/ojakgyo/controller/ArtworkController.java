package com.ojakgyo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ojakgyo.model.Artwork;
import com.ojakgyo.service.ArtworkService;

@RestController
public class ArtworkController {
	
	@Autowired
	private ArtworkService service;
	
//// optional!!!?????????
	Optional<Artwork> op;
	
//	Autowired	쓰면 아래는 생략 가능
//	ArtworkController(ArtworkService ArtworkServiceImpl) {
//		this.service = ArtworkServiceImpl;
//	} 
	
	@GetMapping("/artworks")
	List<Artwork> FindAllArtworks(){
		return service.findbyId(id).orElseThrow(() -> new ArtworkNotFoundException(title));
	
	}
	//null일 경우 예외 발생 자바 신문법으로
//	throw new EmployeeNotFoundException();
	//return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

	@GetMapping("/artworks/{title}")
	List<Artwork> findArtworkByTitle(@PathVariable String title){
		return service.findArtworkByTitle(title).orElseThrow(() -> new ArtworkNotFoundException(title));
	}
	
	
	/* 읽기에 필요한 기능 뭐가 있을까
	 * 	스크롤하면 포스팅한 작품 리스트 로딩하기
	 * 	작품 클릭 -> 개별 페이지로 들어가기 -> 이미지&댓글 등등 다 보임
	 */

}
