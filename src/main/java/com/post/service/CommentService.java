package com.post.service;

import java.util.List;

import com.post.model.Comment;

public interface CommentService {
	Comment createComment(int postId, Comment comment);
	List<Comment> getAllCommentsByPostId(int postId);
	List<Comment> getAllComments();
	Comment getCommentById(int postId, int commentId);
	Comment updateComment(int postId, int commentId, Comment comment);
	String deleteComment(int postId, int commentId);
}
