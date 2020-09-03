package com.ojakgyo.serviceImpl;

import org.springframework.stereotype.Service;

import com.ojakgyo.model.Member;
import com.ojakgyo.repository.MemberRepository;
import com.ojakgyo.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository repository;

	MemberServiceImpl(MemberRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean signUp(Member member) {
		System.out.println(repository.save(member));
		if (repository.save(member) != null) return true;
		return false;
	}

	@Override
	public Member findByNickName(String email, String password) {
//		repository.findByNickNameAndPassword();
		
		return null;
	}
	
	@Override
	public String findEmailByNameAndPhone(String name, String phone) {
		return repository.findEmailByNameAndPhone(name, phone).getEmail();
	
	}
}
