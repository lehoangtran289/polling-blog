package com.example.polls.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.polls.model.User;
import com.example.polls.payload.response.PagedResponse;
import com.example.polls.payload.response.PollResponse;
import com.example.polls.payload.response.UserIdentityAvailability;
import com.example.polls.payload.response.UserProfile;
import com.example.polls.payload.response.UserSummary;
import com.example.polls.repository.PollRepository;
import com.example.polls.repository.UserRepository;
import com.example.polls.repository.VoteRepository;
import com.example.polls.security.CurrentUser;
import com.example.polls.security.ResourceNotFoundException;
import com.example.polls.security.UserPrincipal;
import com.example.polls.service.PollService;
import com.example.polls.util.AppConstants;

@RestController
@RequestMapping("/api")
public class UserController {
	/*
	 * In UserController, We’ll be writing APIs to 
	 * - Get the currently logged in user. 
	 * - Check if a username is available for registration. 
	 * - Check if an email is available for registration. 
	 * - Get the public profile of a user. 
	 * - Get a paginated list of polls created by a given user. 
	 * - Get a paginated list of polls in which a given user has voted.
	 */

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private PollRepository pollRepository;

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PollService pollService;

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
	}

	@GetMapping("/user/checkUsernameAvailability")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = !userRepository.existsByUsername(username);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/user/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByUsername(email);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/users/{username}")
	public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

		long pollCount = pollRepository.countByCreatedBy(user.getId());
		long voteCount = voteRepository.CountByUserId(user.getId());

		UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(),
				pollCount, voteCount);

		return userProfile;

	}

	@GetMapping("users/{username}/polls")
	public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
			@CurrentUser UserPrincipal currentUser,
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
		return pollService.getPollsCreatedBy(username, currentUser, page, size);
	}
	
	@GetMapping("users/{username}/votes")
	public PagedResponse<PollResponse> getPollsVotedBy(@PathVariable(value = "username") String username,
			@CurrentUser UserPrincipal currentUser,
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
		return pollService.getPollsVotedBy(username, currentUser, page, size);
	}
	
	
	
}
