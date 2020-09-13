package com.ojakgyo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ojakgyo.exception.NotFoundException;
import com.ojakgyo.service.CommentService;

@RequestMapping("/comments")
@RestController
public class CommentController {

		private final CommentService commentService;
		
		private Map<String, Object> resultMap;

		public CommentController(CommentService commentService) {
			this.commentService = commentService;
		}
		
		
		/**
		 * 해당 게시글의 댓글 보기
		 * 
		 * @author WON
		 * @param artworkId
		 */
		@GetMapping("/{artworkId}")
		public ResponseEntity<Map<String, Object>> commentsByartworkId(@PathVariable Long artworkId){
			
			resultMap = new HashMap<>();
			
			resultMap.put("dtolist", commentService.commentsByartworkId(artworkId));

			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		}
		
		
		/**
		 * 댓글 작성 기능.
		 * 
		 * @author YOO, WON
		 * @param  Long artworkId
		 * @param  String memberEmail
		 * @param  String content
		 */
		@PostMapping()
		public ResponseEntity<Map<String, Object>> wrtieComment(@RequestParam("artworkId") Long artworkId, @RequestParam("memberEmail") String memberEmail, @RequestParam("content") String content){
			
			resultMap = new HashMap<>();
			
			resultMap.put("dto", Optional.ofNullable(commentService.writeComment(artworkId, memberEmail, content)).orElseThrow(() -> new NotFoundException("댓글 입력 실패")));
			
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		}
		
		/**
		 * 댓글 삭제 기능.
		 * 
		 * @author YOO, WON
		 * @param  commentId
		 */
		@DeleteMapping("/{commentId}")
		public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable Long commentId){
			
			resultMap = new HashMap<>();
			
			commentService.deleteComment(commentId);
			
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		}
	
	
}
