package com.ojakgyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ojakgyo.model.Member;

//@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{//엔티티 클래스, 엔티티 클래스가 사용하는 식별자 타입.
	//JpaRepository는 CRUD처리를 위한 공통 인터페이스임.
	//이 인터페이스를 상속받은 인터페이스만 생성하면 해당 엔티티에 대한 CRUD를 공짜로 사용할 수 있게 됨.
//	Member findByNickName();
	
	Member findMemberByEmailAndPassword(String email, String password);
	Member findEmailByNameAndPhone(String name, String phone);
}
/**
 * 명명규칙
 * find엔티티By변수명() or findBy변수명()
 * 
 */
