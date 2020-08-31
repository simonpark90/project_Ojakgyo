package com.ojakgyo.model.repository;


import javax.persistence.Column;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojakgyo.model.Member;



public interface MemberRepository extends JpaRepository<Member, Long>{
	
}
