package com.example.polls.payload.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.polls.model.Choice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollRequest {
	@NotBlank
	@Size(max = 400)
	private String question;
	
	@NotNull
	@Valid
	@Size(min = 2, max = 10)
	private List<Choice> choices;
	
	@NotNull
	@Valid
	private PollLength pollLength;
}
