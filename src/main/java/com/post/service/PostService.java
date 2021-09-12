package com.post.service;

import java.util.List;
import com.post.model.Post;

public interface PostService {

	Post createPost(Post post);
	List<Post> getAllPost();
	Post getPostById(int postId);
	Post updatePost(int postId,Post post);
	String deletePost(int postId);
	
}
