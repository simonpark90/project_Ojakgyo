package com.ojakgyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ojakgyo.model.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findMemberByEmailAndPassword(String email, String password);

	Member findByEmail(String email);

	Member findEmailByNameAndPhone(String name, String phone);

	@Query(value = "SELECT * FROM Member WHERE member_nickname LIKE %?1%", nativeQuery = true)
	Member findMember(String searchKeyWord);

}
