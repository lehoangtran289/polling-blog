package com.example.polls.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChoiceVoteCount {
	// The ChoiceVoteCount class is used in VoteRepository to return custom results from the query.
	private Long choiceId;
	private Long voteCount;
}
