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

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Artwork {      
   @Id @GeneratedValue
   @Column(name="ARTWORK_ID")
   private int id;
   
   @Column(name="ARTWORK_TITLE")
   private String title;
   
   @Column(name="ARTWORK_NICKNAME")
   private String nickname;
   
   @Column(name="ARTWORK_LIKE")
   private String like;

   @Column(name="ARTWORK_CONTENT")
   private String content;
   
   @ManyToOne(fetch =FetchType.LAZY)
   @JoinColumn(name = "MEMBER_ID")
   private Member member;
   
   @OneToMany(mappedBy = "artwork")
   private List<Comment> comments = new ArrayList<>();

   @OneToMany(mappedBy = "artwork")
   private List<Image> images = new ArrayList<>();
   
   @Builder
   public Artwork(String title, String nickname, String like, String content, Member member, List<Comment> comments,
		List<Image> images) {
		
	   this.title = title;
	   this.nickname = nickname;
	   this.like = like;
	   this.content = content;
	   this.member = member;
	   this.comments = comments;
	   this.images = images;	
}
   
   
}