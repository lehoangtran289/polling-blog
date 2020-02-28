package com.example.Reactdemo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Reactdemo.model.Post;
import com.example.Reactdemo.service.PostService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class PostController {
	private final Logger log = LoggerFactory.getLogger(PostController.class);

	@Autowired
	PostService postService;

	@GetMapping("/post/list")
	List<Post> list() {
		return postService.listPost();
	}

	@GetMapping("/post/{id}")
	ResponseEntity<?> getPost(@PathVariable("id") Long postId) {
		return postService.findById(postId);
	}

	@PostMapping("/post")
	ResponseEntity<Post> createPost(@Valid @RequestBody Post post) throws URISyntaxException {
		Post newPost = postService.savePost(post);
		return ResponseEntity.created(new URI("api/post" + newPost.getId())).body(newPost);
	}

	@PutMapping("/post/{id}")
	ResponseEntity<Post> updatePost(@PathVariable("id") Long postId, @Valid @RequestBody Post post) {
		Post newpost = postService.savePost(post);
		return ResponseEntity.ok().body(newpost);
	}

	@DeleteMapping("/post/{id}")
	ResponseEntity<?> deletePost(@PathVariable("id") Long postId) {
		postService.deletePost(postId);
		return ResponseEntity.ok().build();
	}

}
