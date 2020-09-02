package com.ojakgyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojakgyo.model.Blame;

//@Repository
public interface BlameRepository extends JpaRepository<Blame, Long>{
	
}
