package com.ojakgyo.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@DynamicInsert
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)
@Entity @Table(name = "MEMBER")
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
	@Column(name = "MEMBER_ID")
	private Long id;

	@NotBlank(message = "닉네임이 걸림") // null + "" + " "
	@Column(name = "MEMBER_NICKNAME", nullable = false, unique = true)
	private String nickname;

	@NotBlank(message = "이메일이 걸림")
	@Column(name = "MEMBER_EMAIL", nullable = false, unique = true)
	private String email;

	@NotBlank(message = "비밀번호가 걸림")
	@Column(name = "MEMBER_PASSWORD", nullable = false)
	private String password;

	@NotBlank(message = "이름이 걸림")
	@Column(name = "MEMBER_NAME", nullable = false)
	private String name;

	@NotBlank(message = "핸드폰이 걸림")
	@Column(name = "MEMBER_PHONE", nullable = false, unique = true)
	private String phone;

	@Column(name = "MEMBER_PROFILE")
	private String profile;

	@ColumnDefault("1")
	@Column(name = "MEMBER_LEVEL", nullable = false)
	private String level; // 사용자 or 관리자

	@OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Artwork> artworks = new ArrayList<>();

	@OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	// 연관관계 메소드
	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setMember(this);
	}

	@Builder
	public Member(@NotBlank String nickname, @NotBlank String email, @NotBlank String password, @NotBlank String name,
			@NotBlank String phone, String profile, @NotBlank String level, List<Artwork> artworks,
			List<Comment> comments) {
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.profile = profile;
		this.level = level;
		this.artworks = artworks;
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", nickname=" + nickname + ", email=" + email + ", password=" + password + ", name="
				+ name + ", phone=" + phone + ", profile=" + profile + ", level=" + level + ", artworks=" + artworks
				+ ", comments=" + comments + "]";
	}
	

}