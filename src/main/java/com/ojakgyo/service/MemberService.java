package com.ojakgyo.service;

import com.ojakgyo.model.dto.MemberDto;

public interface MemberService {
	MemberDto signUp(MemberDto memberDto);

	MemberDto emailDuplCheck(String email);

	String findEmailByNameAndPhone(String name, String phone);

	MemberDto signInCheck(MemberDto memberDto);
}
