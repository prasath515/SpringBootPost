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

import com.post.model.Post;
import com.post.service.PostService;

@RestController
@RequestMapping("/api/v1")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping(value = "/posts")
	private ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
		return new ResponseEntity<Post>(postService.createPost(post),HttpStatus.CREATED);
	}

	@GetMapping("/posts")
	private ResponseEntity<List<Post>> getAllPost(){
		return new ResponseEntity<List<Post>>(postService.getAllPost(),HttpStatus.OK);
	}
	
	@GetMapping("/posts/{id}")
	private ResponseEntity<Post> getPostById(@PathVariable("id") int postId){
		return new ResponseEntity<Post>(postService.getPostById(postId),HttpStatus.OK);
	}
	
	@PutMapping("/posts/{id}")
	private ResponseEntity<Post> updatePost(@Valid @RequestBody Post post, @PathVariable("id") int postId){
		return new ResponseEntity<Post>(postService.updatePost(postId, post), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/posts/{id}", produces = "application/json")
	private ResponseEntity<String> deletePost(@PathVariable("id") int postId){
		return new ResponseEntity<String>(postService.deletePost(postId), HttpStatus.OK);
	}
}
