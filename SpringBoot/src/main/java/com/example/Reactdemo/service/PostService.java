package com.example.Reactdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Reactdemo.model.Post;
import com.example.Reactdemo.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public ResponseEntity<?> findById(Long postId) {
		Optional<Post> postOptional = postRepository.findById(postId);
		return postOptional.map(post -> ResponseEntity.ok().body(post))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	public List<Post> listPost() {
		return postRepository.findAll();
	}

	public Post savePost(Post post) {
		return postRepository.save(post);
	}

	public void deletePost(Long postId) {
		postRepository.deleteById(postId);
	}

}
