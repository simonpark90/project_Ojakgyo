package com.ojakgyo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.beans.factory.annotation.Value;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "MEMBER")
@NoArgsConstructor
@DynamicInsert
public class Member{
   
   @Id @GeneratedValue
   @Column(name = "MEMBER_ID") // 주의
   private int id;
   
   @NotBlank
   @Column(name = "MEMBER_NICKNAME", nullable = false)
   private String nickname;
   
   @NotBlank
   @Column(name = "MEMBER_EMAIL", nullable = false)
   private String email;
   
   @NotBlank
   @Column(name = "MEMBER_PASSWORD", nullable = false)
   private String password;
   
   @NotBlank
   @Column(name = "MEMBER_NAME", nullable = false)
   private String name;
   
   @NotBlank
   @Column(name = "MEMBER_PHONE", nullable = false)
   private String phone;
  
  
   @Column(name = "MEMBER_PROFILE")
   private String profile;
   
   @ColumnDefault("1")
   @Column(name = "MEMBER_LEVEL", nullable = false)
   private String level;

   
   @OneToMany(mappedBy = "member")
   private List<Artwork> artworks = new ArrayList<>();
   
   @OneToMany(mappedBy = "member")
   private List<Comment> comments = new ArrayList<>();

   
   


   
   
   
   
   
}