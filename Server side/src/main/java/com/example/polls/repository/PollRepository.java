package com.example.polls.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.polls.model.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long>{
	Optional<Poll> findById(Long id);
	
	// find all post by an user
	Page<Poll> findByCreatedBy(Long userId, Pageable pageable);
	
	// count all post by an user
	long countByCreatedBy(Long userId);
	
	List<Poll> findByIdIn(List<Long> pollIds); //In = in list of Id
	
	List<Poll> findByIdIn(List<Long> pollIds, Sort sort);
}
