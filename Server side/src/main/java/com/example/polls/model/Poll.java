package com.example.polls.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.example.polls.model.audit.UserDateAudit;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "polls")
@Getter
@Setter
public class Poll extends UserDateAudit { //createdAt, updatedAt, createdBy, updatedBy
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 1000)
	private String question;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "poll", orphanRemoval = true) // Choice class
	@Size(min = 2, max = 10)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 30) // fetch 30 records per call
	private List<Choice> choices = new ArrayList<>();

	@NotNull
	private Instant expirationDateTime;

	public void addChoice(Choice choice) {
		choices.add(choice);
		choice.setPoll(this);
	}

	public void removeChoice(Choice choice) {
		choices.remove(choice);
		choice.setPoll(null);
	}

}
