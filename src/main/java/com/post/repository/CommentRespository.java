package com.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.post.model.Comment;

public interface CommentRespository extends JpaRepository<Comment, Integer>{

	Optional<Comment> findByIdAndPostId(int commentId, int postId);
	Optional<List<Comment>> findByPostId(int PostId);
}
