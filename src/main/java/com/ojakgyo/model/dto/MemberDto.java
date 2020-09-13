package com.ojakgyo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberDto {

	private Long id;

	private String nickname;

	private String email;

	private String password;

	private String name;

	private String phone;

	private String profile;

	private String level; // 사용자 or 관리자

	@Builder
	public MemberDto(Long id, String nickname, String email, String password, String name, String phone, String profile,
			String level) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.profile = profile;
		this.level = level;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", nickname=" + nickname + ", email=" + email + ", password=" + password
				+ ", name=" + name + ", phone=" + phone + ", profile=" + profile + ", level=" + level + "]";
	}

}