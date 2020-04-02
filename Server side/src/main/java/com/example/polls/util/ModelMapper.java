package com.example.polls.util;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.polls.model.Poll;
import com.example.polls.model.User;
import com.example.polls.payload.response.ChoiceResponse;
import com.example.polls.payload.response.PollResponse;
import com.example.polls.payload.response.UserSummary;

public class ModelMapper {
	
	// mapping the Poll entity to a PollResponse payload which contains a bunch of information
	
	public static PollResponse mapPollToPollResponse(Poll poll, Map<Long, Long> choiceVotesMap, User creator, Long userVote) {
		PollResponse pollResponse = new PollResponse();
		pollResponse.setId(poll.getId());
		pollResponse.setQuestion(poll.getQuestion());
		pollResponse.setCreationDateTime(poll.getCreatedAt());
		pollResponse.setExpirationDateTime(poll.getExpirationDateTime());
		
		Instant now = Instant.now();
		pollResponse.setIsExpired(poll.getExpirationDateTime().isBefore(now));
		
		// map list of choices in a poll to list of choiceResponses
		List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(choice ->{
			ChoiceResponse choiceResponse = new ChoiceResponse();
			choiceResponse.setId(choice.getId());
			choiceResponse.setText(choice.getText());
			if(choiceVotesMap.containsKey(choice.getId())) // if a choice has any votes
				choiceResponse.setVoteCount(choiceVotesMap.get(choice.getId()));
			else
				choiceResponse.setVoteCount(0);
			return choiceResponse;
		}).collect(Collectors.toList());
		pollResponse.setChoices(choiceResponses);
		
		// set user creator information
		UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
		pollResponse.setCreatedBy(creatorSummary);
		
		// selected choices
		if (userVote != null)
			pollResponse.setSelectedChoice(userVote);
		
		// total votes of a poll
		long totalVotes = pollResponse.getChoices().stream().mapToLong(ChoiceResponse::getVoteCount).sum();
		pollResponse.setTotalVotes(totalVotes);
		
		return pollResponse;
	}
}
