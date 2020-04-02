package com.example.polls.payload.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PollLength {
	@NotNull
	@Max(7)
	private Integer days;
	
	@NotNull
	@Max(23)
	private Integer hours;
}
