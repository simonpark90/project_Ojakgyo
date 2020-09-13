package com.ojakgyo.serviceImpl;


import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.ojakgyo.model.dto.CommentDto;
import com.ojakgyo.model.entity.Artwork;
import com.ojakgyo.model.entity.Comment;
import com.ojakgyo.model.entity.Member;
import com.ojakgyo.repository.ArtworkRepository;
import com.ojakgyo.repository.CommentRepository;
import com.ojakgyo.repository.MemberRepository;
import com.ojakgyo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentRepository commentRepository;
	
	private final MemberRepository memberRepository;
	
	private final ArtworkRepository artworkRepository;
	
	private final ModelMapper modelMapper;

	public CommentServiceImpl(CommentRepository commentRepository,MemberRepository memberRepository, ArtworkRepository artworkRepository ,ModelMapper modelMapper) {
		this.commentRepository = commentRepository;
		this.memberRepository = memberRepository;
		this.artworkRepository = artworkRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public CommentDto writeComment(Long artworkId, String memberEmail, String content) {
		
		Artwork artwork = artworkRepository.findById(artworkId).get();
		Member member = memberRepository.findByEmail(memberEmail);
		Comment comment = Comment.builder().content(content).writeDate(new Date()).writer(member.getNickname()).build();

		artwork.addComment(comment);
		member.addComment(comment);
		
		return modelMapper.map(commentRepository.save(comment), CommentDto.class);
	}

	@Override
	public void deleteComment(Long commentId) {
		commentRepository.delete(commentRepository.findById(commentId).get());
	}


	//댓글 검색
	@Override
	public List<CommentDto> commentsByartworkId(Long artworkId) {
		return modelMapper.map(commentRepository.findCommentByArtworkId(artworkId), new TypeToken<List<CommentDto>>() {}.getType());
	}
	
	
}
