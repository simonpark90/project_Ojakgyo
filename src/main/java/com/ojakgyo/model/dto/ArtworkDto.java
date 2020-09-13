package com.ojakgyo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ArtworkDto {

	private Long id;

	private String title;

	private int like;

	private String content;

	@Builder
	public ArtworkDto(Long id, String title, int like, String content) {
		this.id = id;
		this.title = title;
		this.like = like;
		this.content = content;
	}

	@Override
	public String toString() {
		return "ArtworkDto [id=" + id + ", title=" + title + ", like=" + like + ", content=" + content + "]";
	}

}
