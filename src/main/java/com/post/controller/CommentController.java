package com.post.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.post.model.Comment;
import com.post.service.CommentService;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/posts/{postId}/comments")
	private ResponseEntity<Comment> createComment(@PathVariable("postId") int postId,
			@Valid @RequestBody Comment comment) {
		return new ResponseEntity<Comment>(commentService.createComment(postId, comment), HttpStatus.CREATED);
	}

	@GetMapping("/comments")
	private ResponseEntity<List<Comment>> getAllComments() {
		return new ResponseEntity<List<Comment>>(commentService.getAllComments(), HttpStatus.OK);
	}

	@GetMapping("posts/{postId}/comments")
	private ResponseEntity<List<Comment>> getAllCommentsByPostId(@PathVariable("postId") int postId) {
		return new ResponseEntity<List<Comment>>(commentService.getAllCommentsByPostId(postId), HttpStatus.OK);
	}

	@GetMapping("posts/{postId}/comments/{commentId}")
	private ResponseEntity<Comment> getCommentById(@PathVariable("postId") int postId,
			@PathVariable("commentId") int commentId) {
		return new ResponseEntity<Comment>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}/comments/{commentId}")
	private ResponseEntity<Comment> updateComment(@PathVariable("postId") int postId,
			@PathVariable("commentId") int commentId, @Valid @RequestBody Comment comment) {

		return new ResponseEntity<Comment>(commentService.updateComment(postId, commentId, comment), HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postid}/comments/{commentId}")
	private ResponseEntity<String> deleteComment(@PathVariable("postid") int postId,
			@PathVariable("commentId") int commentId) {
		return new ResponseEntity<String>(commentService.deleteComment(postId, commentId), HttpStatus.OK);
	}
}
