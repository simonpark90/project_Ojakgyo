package com.ojakgyo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojakgyo.model.Artwork;

//@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long>{
	//JpaRepository는 CRUD처리를 위한 공통 인터페이스임.
	//이 인터페이스를 상속받은 인터페이스만 생성하면 해당 엔티티에 대한 CRUD를 공짜로 사용할 수 있게 됨.
	//title로 검색(작가로 검색하는 기능은 안 넣기로 함)
	public List<Artwork> findArtworkByTitleContaining(String title);

}
/**
 * 명명규칙
 * find엔티티By변수명() or findBy변수명()
 * 
 */