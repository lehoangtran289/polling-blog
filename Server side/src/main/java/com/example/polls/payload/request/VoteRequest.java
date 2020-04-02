package com.example.polls.payload.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteRequest {
	@NotNull
	private Long choiceId;
}
