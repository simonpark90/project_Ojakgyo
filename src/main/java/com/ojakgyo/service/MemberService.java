package com.ojakgyo.service;

import com.ojakgyo.model.Member;

public interface MemberService {
	boolean signUp(Member member);

	Member findByNickName(String email, String password);
}
