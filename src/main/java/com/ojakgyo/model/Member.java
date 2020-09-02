package com.ojakgyo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicInsert
@NoArgsConstructor
@Getter
@Entity
public class Member {
   @Id @GeneratedValue
   @Column(name = "MEMBER_ID") // 주의!!
   private int id;
   
   @NotBlank(message = "닉네임이 걸림") //null + "" + " "
   @Column(name = "MEMBER_NICKNAME", nullable = false)
   private String nickname;
   
   @NotBlank(message = "이메일이 걸림") 
   @Column(name = "MEMBER_EMAIL", nullable = false)
   private String email;
   
   @NotBlank(message = "비밀번호가 걸림")
   @Column(name = "MEMBER_PASSWORD", nullable = false)
   private String password;
   
   @NotBlank(message = "이름이 걸림")
   @Column(name = "MEMBER_NAME", nullable = false)
   private String name;

   @NotBlank(message = "핸드폰이 걸림")
   @Column(name = "MEMBER_PHONE", nullable = false)
   private String phone;

   @Column(name = "MEMBER_PROFILE")
   private String profile;
   
   @ColumnDefault("1")
   @Column(name = "MEMBER_LEVEL", nullable = false)
   private String level; // 사용자 or 관리자
   
   @OneToMany(mappedBy = "member")
   private List<Artwork> artworks = new ArrayList<>();

   @OneToMany(mappedBy = "member")
   private List<Comment> comments = new ArrayList<>();

   @Builder
   public Member(@NotBlank String nickname, @NotBlank String email, @NotBlank String password, @NotBlank String name,
         @NotBlank String phone, String profile, @NotBlank String level, List<Artwork> artworks,
         List<Comment> comments) {
      this.nickname = nickname;
      this.email = email;
      this.password = password;
      this.name = name;
      this.phone = phone;
      this.profile = profile;
      this.level = level;
      this.artworks = artworks;
      this.comments = comments;
   }
   
}