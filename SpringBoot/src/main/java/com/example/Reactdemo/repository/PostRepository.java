package com.example.Reactdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Reactdemo.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
