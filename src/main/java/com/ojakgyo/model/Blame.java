package com.ojakgyo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Blame {
	@Id
	@GeneratedValue
	@Column(name = "BLAME_ID")
	private int id;

	@Column(name = "BLAME_TARGET_ID")
	private String targetId;// 신고한 전시물이나 댓글의 PK

	@Column(name = "BLAME_TARGET_TYPE")
	private String targetType;// 1이면 전시물, 2이면 댓글

	@Column(name = "BLAME_DATETIME") // 신고날짜
	private Date dateTime;

	@Column(name = "BLAME_TARGET_MEMBER_ID") // 신고당한놈
	private int targetMemberId;

	@Column(name = "BLAME_MEMBER_ID") // 신고한놈
	private int memberId;
	
	@Builder
	public Blame(String targetId, String targetType, Date dateTime, int targetMemberId, int memberId) {
		this.targetId = targetId;
		this.targetType = targetType;
		this.dateTime = dateTime;
		this.targetMemberId = targetMemberId;
		this.memberId = memberId;
	}


}
