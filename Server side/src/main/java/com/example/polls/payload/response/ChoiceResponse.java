package com.example.polls.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChoiceResponse {
	private long id;

	private String text;

	private long voteCount;

}
