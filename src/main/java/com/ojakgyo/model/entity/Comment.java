package com.ojakgyo.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Entity @Table(name = "COMMENTO")
@SequenceGenerator(name = "COMMENTO_SEQ_GENERATOR", sequenceName = "COMMENTO_SEQ", initialValue = 1, allocationSize = 1)
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENTO_SEQ_GENERATOR")
	@Column(name = "COMMENT_ID")
	private Long id;

	@Column(name = "COMMENT_CONTENT")
	private String content;

	@Column(name = "COMMENT_WRITEDATE")
	private Date writeDate;

	@Column(name = "COMMENT_WRITER")
	private String writer;

	@Column(name = "COMMENT_LIKE")
	private int like;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ARTWORK_ID")
	private Artwork artwork;

	@Builder
	public Comment(String content, Date writeDate, String writer, int like, Member member, Artwork artwork) {
		this.content = content;
		this.writeDate = writeDate;
		this.writer = writer;
		this.like = like;
		this.member = member;
		this.artwork = artwork;
	}

}
