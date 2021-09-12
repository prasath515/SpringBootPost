package com.post.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.post.exception.ResourceNotFoundException;
import com.post.model.Comment;
import com.post.repository.CommentRespository;
import com.post.repository.PostRepository;
import com.post.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRespository commentRespository;

	@Override
	public Comment createComment(int postId, Comment comment) {

		return postRepository.findById(postId).map(post -> {
			comment.setPost(post);
			return commentRespository.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("Id","Post is not found for id : "+postId));
	}

	@Override
	public List<Comment> getAllComments() {
		return commentRespository.findAll();
	}

	@Override
	public Comment getCommentById(int postId, int commentId) {
		return commentRespository.findByIdAndPostId(commentId, postId)
				.orElseThrow(() -> new ResourceNotFoundException("Id","Comment is not found for id : "+commentId));
	}

	@Override
	public Comment updateComment(int postId, int commentId, Comment comment) {
		// postRepository.findById(id).orElseThrow(() -> new
		// ResourceNotFoundException("Post", "Id", postId));

		// or

		if (!postRepository.existsById(postId)) {
			throw new ResourceNotFoundException("Id","Post is not found for id :"+postId);
		}
		return commentRespository.findById(commentId).map(com -> {
			com.setText(comment.getText());
			return commentRespository.save(com);
		}).orElseThrow(() -> new ResourceNotFoundException("Id","Comment is not found for id : "+commentId));

	}

	@Override
	public String deleteComment(int postId, int commentId) {
		return commentRespository.findByIdAndPostId(commentId, postId).map(com -> {
			commentRespository.deleteById(commentId);
			return "Comment Deleted Successfully!.";
		}).orElseThrow(() -> new ResourceNotFoundException("Id","Comment is not found for id : "+commentId));
	}

	@Override
	public List<Comment> getAllCommentsByPostId(int postId) {

		return commentRespository.findByPostId(postId)
				.orElseThrow(() ->new ResourceNotFoundException("Id","Post is not found for id : "+postId));

	}

}
