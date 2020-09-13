package com.ojakgyo.model.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CommentDto {

	private Long id;

	private String content;

	private Date writeDate;

	private int like;

	private String writer;

	@Builder
	public CommentDto(Long id, String content, Date writeDate, int like, String writer) {
		this.id = id;
		this.content = content;
		this.writeDate = writeDate;
		this.like = like;
		this.writer = writer;
	}

}
