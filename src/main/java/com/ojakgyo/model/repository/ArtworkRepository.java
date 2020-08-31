package com.ojakgyo.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ojakgyo.model.Member;



public interface ArtworkRepository extends JpaRepository<Member, Long>{
	
}
