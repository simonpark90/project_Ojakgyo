package com.ojakgyo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ojakgyo.model.entity.Image;

public interface UploadFileInfoRepository extends JpaRepository<Image, Long>{
	@Query("select f from Image f where artwork_id = :id")
	List<Image> findAllByArtworkId(Long id);
}
