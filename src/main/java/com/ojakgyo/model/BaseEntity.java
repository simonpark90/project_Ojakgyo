package com.ojakgyo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	
	@CreationTimestamp
	@Column
	protected LocalDateTime createAt;
	
	@UpdateTimestamp
	@Column
	protected LocalDateTime updatedAt;
}
