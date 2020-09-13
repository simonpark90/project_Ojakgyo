package com.ojakgyo.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ojakgyo.model.dto.MemberDto;
import com.ojakgyo.model.entity.Member;
import com.ojakgyo.repository.MemberRepository;
import com.ojakgyo.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	private final ModelMapper modelMapper;

	public MemberServiceImpl(final MemberRepository memberRepository, final ModelMapper modelMapper) {
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public MemberDto signUp(MemberDto memberDto) {
		return modelMapper.map(memberRepository.save(modelMapper.map(memberDto, Member.class)), MemberDto.class);
	}

	@Override
	public MemberDto emailDuplCheck(String email) {
		return modelMapper.map(memberRepository.findByEmail(email), MemberDto.class);
	}

	@Override
	public String findEmailByNameAndPhone(String name, String phone) {
		return memberRepository.findEmailByNameAndPhone(name, phone).getEmail();
	}

	@Override
	public MemberDto signInCheck(MemberDto memberDto) {
		return modelMapper.map(memberRepository.findMemberByEmailAndPassword(memberDto.getEmail(), memberDto.getPassword()), MemberDto.class);
	}

}
