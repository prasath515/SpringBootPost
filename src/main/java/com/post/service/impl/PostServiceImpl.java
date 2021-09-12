package com.post.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.post.exception.ResourceNotFoundException;
import com.post.model.Comment;
import com.post.model.Post;
import com.post.repository.PostRepository;
import com.post.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public Post createPost(Post post) {

		if (postRepository.existsByTitle(post.getTitle())) {
			throw new ResourceNotFoundException("title",
					"The title '" + post.getTitle() + "' is already taken, Please give some other title!.");
		}

		Set<Comment> comments = post.getComment();
		if (comments.size() > 0) {
			comments.forEach(com -> com.setPost(post));
			post.setComment(comments);
		}
		return postRepository.save(post);
	}

	@Override
	public List<Post> getAllPost() {
		return postRepository.findAll();
	}

	@Override
	public Post getPostById(int postId) {
		return postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Id", "Post is not found for id : " + postId));
	}

	@Override
	public Post updatePost(int postId, Post post) {
		Set<Comment> comments = post.getComment();
		if (comments.size() > 0) {
			comments.stream().forEach(com -> com.setPost(post));
		}
		post.setComment(comments);
		return postRepository.save(post);
	}

	@Override
	public String deletePost(int postId) {
		postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Id", "Post is not found for id : " + postId));
		postRepository.deleteById(postId);
//		JSONObject json = new JSONObject();
//		json.put("message", "Post Deleted Successfully!");
		return "Post Deleted Successfully!";
	}

}
