package com.ojakgyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojakgyo.model.Artwork;

//@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long>{
	
}
