package com.ojakgyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojakgyo.model.Image;

//@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
	
}
