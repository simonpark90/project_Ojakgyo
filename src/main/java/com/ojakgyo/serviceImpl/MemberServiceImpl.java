package com.ojakgyo.serviceImpl;

import org.springframework.stereotype.Service;

import com.ojakgyo.model.Member;
import com.ojakgyo.model.repository.MemberRepository;
import com.ojakgyo.service.MemberService;
@Service
public class MemberServiceImpl implements MemberService {
	private final MemberRepository repository;

	MemberServiceImpl(MemberRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean signUp(Member member) {
		System.out.println("member");
		if (repository.save(member) != null) return true;
		return false;
	}
	
}
