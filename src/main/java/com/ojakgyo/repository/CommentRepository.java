package com.ojakgyo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ojakgyo.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	@Query(value = "select * from Commento c inner join Artwork a on c.artwork_id=a.artwork_id where a.artwork_id = :artworkId order by c.comment_id", nativeQuery = true)
	List<Comment> findCommentByArtworkId(@Param("artworkId") Long artworkId);
}
