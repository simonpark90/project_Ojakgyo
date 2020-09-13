package com.ojakgyo.service;

import java.util.List;

import com.ojakgyo.model.dto.CommentDto;

public interface CommentService {

	// 댓글 등록
	CommentDto writeComment(Long artworkId, String memberEmail, String content);
	// 댓글 삭제
	void deleteComment(Long commentId);
	// 댓글 조회
	List<CommentDto> commentsByartworkId(Long artworkId);
	
}