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
	private String targetId;
	
	@Column(name = "BLAME_TARGET_TYPE")
	private String targetType;
	
	@Column(name = "BLAME_DATETIME")
	private Date dateTime;
	
	@Column(name = "BLAME_TARGET_MEMBER_ID")
	private String targetMemberId;
	
	@Column(name = "BLAMETARGET_MEMBER_ID")
	private String MemberId;

	@Builder
	public Blame(String targetId, String targetType, Date dateTime, String targetMemberId, String memberId) {
		this.targetId = targetId;
		this.targetType = targetType;
		this.dateTime = dateTime;
		this.targetMemberId = targetMemberId;
		MemberId = memberId;
	}
	
	

}
