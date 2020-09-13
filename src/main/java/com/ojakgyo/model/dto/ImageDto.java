package com.ojakgyo.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ImageDto {

	private Long id;

	private String imageName;

	private String imagePath;

	private String imageType;

	private Long imageSize;

	@Builder
	public ImageDto(Long id, String imageName, String imagePath, String imageType, Long imageSize) {
		this.id = id;
		this.imageName = imageName;
		this.imagePath = imagePath;
		this.imageType = imageType;
		this.imageSize = imageSize;
	}

	@Override
	public String toString() {
		return "ImageDto [id=" + id + ", imageName=" + imageName + ", imagePath=" + imagePath + ", imageType="
				+ imageType + ", imageSize=" + imageSize + "]";
	}

}
