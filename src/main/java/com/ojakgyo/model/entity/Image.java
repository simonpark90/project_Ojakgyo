package com.ojakgyo.model.entity;

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

@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(name = "IMAGE_SEQ_GENERATOR", sequenceName = "IMAGE_SEQ", initialValue = 1, allocationSize = 1)
@Entity @Table(name = "IMAGE")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_SEQ_GENERATOR")
	@Column(name = "IMAGE_ID")
	private Long id;

	@Column(name = "IMAGE_NAME")
	private String imageName;

	@Column(name = "IMAGE_PATH") // FileDownloadUri
	private String imagePath;

	@Column(name = "IMAGE_TYPE")
	private String imageType;

	@Column(name = "IMAGE_FILETYPE")
	private Long imageSize;

	// 하나의 artwork는 여러개의 image를 가질 수 있음.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ARTWORK_ID", insertable = true, updatable = false)
	private Artwork artwork;

	@Builder
	public Image(Long id, String imageName, String imagePath, String imageType, Long imageSize, Artwork artwork) {
		this.id = id;
		this.imageName = imageName;
		this.imagePath = imagePath;
		this.imageType = imageType;
		this.imageSize = imageSize;
		this.artwork = artwork;
	}

}
