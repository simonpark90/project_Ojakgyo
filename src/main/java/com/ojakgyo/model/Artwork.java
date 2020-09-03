package com.ojakgyo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter @NoArgsConstructor
@Table(name = "ARTWORK")
public class Artwork {
	@Id @GeneratedValue
	@Column(name = "ARTWORK_ID")//주의
	private int id;
	@Column(name = "ARTWORK_TITLE")
	private String title;
	@Column(name = "ARTWORK_LIKE")
	private int like;
	@Column(name = "ARTWORK_CONTENT")//사진관련 글
	private String content;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	@OneToMany(mappedBy = "artwork")// 댓글
	private List<Comment> comments = new ArrayList<>();
	
	@OneToMany(mappedBy = "artwork")// 사진 list
	private List<Image> images = new ArrayList<>();
	
	@Builder
	public Artwork(int id, String title, String content, int like) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.like = like;
	}
	
	
}
