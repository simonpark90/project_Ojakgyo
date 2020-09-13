package com.ojakgyo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ojakgyo.model.entity.Image;

//@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	List<Image> findByImageNameContaining(@Param("keyword") String keyword);
}
