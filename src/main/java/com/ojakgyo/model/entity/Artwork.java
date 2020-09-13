package com.ojakgyo.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@SequenceGenerator(name = "ARTWORK_SEQ_GENERATOR", sequenceName = "ARTWORK_SEQ", initialValue = 1, allocationSize = 1)
@Entity @Table(name = "ARTWORK")
public class Artwork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTWORK_SEQ_GENERATOR")
	@Column(name = "ARTWORK_ID")
	private Long id;

	@Column(name = "ARTWORK_TITLE", nullable = false)
	private String title;

	@Column(name = "ARTWORK_LIKE")
	private int like;

	@Column(name = "ARTWORK_CONTENT", nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@OneToMany(mappedBy = "artwork", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "artwork", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Image> images = new ArrayList<>();

	// ==연관관계 메서드==//
	public void setMember(Member member) {
		this.member = member;
		member.getArtworks().add(this);
	}

	public void addImage(Image image) {
		images.add(image);
		image.setArtwork(this);
	}

	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setArtwork(this);
	}

	@Builder
	public Artwork(String title, int like, String content, Member member, List<Comment> comments, List<Image> images) {
		this.title = title;
		this.like = like;
		this.content = content;
		this.member = member;
		this.comments = comments;
		this.images = images;
	}

	@Override
	public String toString() {
		return "Artwork [id=" + id + ", title=" + title + ", like=" + like + ", content=" + content + ", member="
				+ member + ", comments=" + comments + ", images=" + images + "]";
	}

}
