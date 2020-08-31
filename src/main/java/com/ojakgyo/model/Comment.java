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

@NoArgsConstructor
@Getter
@Entity
@Table(name="COMMENTO")
public class Comment {
   @Id @GeneratedValue
   private int id;
   
   @Column(name = "COMMENT_ID")
   private String content;
   
   @Column(name = "COMMENT_WRITEDATE")
   private Date WriteDate;
   
   @Column(name = "COMMENT_LIKE")
   private int like;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="MEMBER_ID")
   private Member member;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="artwork_ID")
   private Artwork artwork;

   @Builder
   public Comment(String content, Date writeDate, int like, Member member, Artwork artwork) {
	   super();
	   this.content = content;
	   WriteDate = writeDate;
	   this.like = like;
	   this.member = member;
	   this.artwork = artwork;
}
   
   
   
}