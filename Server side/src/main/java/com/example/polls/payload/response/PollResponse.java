package com.example.polls.payload.response;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollResponse {
	private Long id;

	private String question;

	private List<ChoiceResponse> choices;

	private UserSummary createdBy;

	private Instant creationDateTime;

	private Instant expirationDateTime;

	private Boolean isExpired;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long selectedChoice;

	private Long totalVotes;
}
