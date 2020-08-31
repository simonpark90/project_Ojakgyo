package com.ojakgyo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Image {
   @Id @GeneratedValue
   @Column(name = "IMAGE_ID")
   private int id;
   
   @Column(name = "IMAGE_PATH")
   private String imagePath;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ARTWORK_ID")
   private Artwork artwork;

   @Builder
   public Image(String imagePath, Artwork artwork) {
	   super();
	   this.imagePath = imagePath;
	   this.artwork = artwork;
}
   
   
   
}