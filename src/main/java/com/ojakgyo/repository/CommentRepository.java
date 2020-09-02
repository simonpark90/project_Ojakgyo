package com.ojakgyo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojakgyo.model.Comment;

//@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
}
