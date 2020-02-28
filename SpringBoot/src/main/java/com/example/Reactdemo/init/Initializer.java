package com.example.Reactdemo.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Reactdemo.model.Post;
import com.example.Reactdemo.repository.PostRepository;

@Component
public class Initializer implements CommandLineRunner {
	private final PostRepository postRepository;

	public Initializer(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		// Stream.of("Post 1", "Post 2", "Post 3", "Post 4", "Post 5").forEach(content
		// -> postRepository.save(new Post(content)));
		postRepository.save(new Post("This is the content of Post 1"));
		postRepository.save(new Post("This is the content of Post 2"));
		postRepository.save(new Post("This is the content of Post 4"));
		postRepository.save(new Post("This is the content of Post 5"));
		postRepository.save(new Post("This is the content of Post 6"));
		postRepository.save(new Post("This is the content of Post 7"));

		postRepository.findAll().forEach(System.out::println);
	}
}
