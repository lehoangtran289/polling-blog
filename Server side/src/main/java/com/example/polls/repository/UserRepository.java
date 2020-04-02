package com.example.polls.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.polls.model.User;
import java.lang.String;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String Email);
	
	Optional<User> findByUsernameOrEmail(String username, String email);
	
	Optional<User> findByUsername(String username);
	
	List<User> findByIdIn(List<Long> userIds);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);

}
