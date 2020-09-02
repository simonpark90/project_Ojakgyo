package com.ojakgyo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "COMMENTO")
public class Comment {
	
	@Id @GeneratedValue
	@Column(name = "COMMENT_ID")
	private int id; 
	
	@Column(name = "COMMENT_CONTENT")
	private String content;
	
	@Column(name = "COMMENT_WRITEDATE")
	private Date writeDate;
	
	@Column(name = "COMMENT_LIKE")
	private int like;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ARTWORK_ID")
	private Artwork artwork;

	@Builder
	public Comment(int id, String content, Date writeDate, int like, Member member, Artwork artwork) {
		super();
		this.id = id;
		this.content = content;
		this.writeDate = writeDate;
		this.like = like;
		this.member = member;
		this.artwork = artwork;
	}
	
	
	
}
