package com.example.polls.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "choices")
@Getter
@Setter
@NoArgsConstructor
public class Choice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="poll_id", nullable=false)
	private Poll poll;
	
	public Choice(String text) {
		this.text = text;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Choice choice = (Choice) obj;
		return Objects.equals(choice.id, id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
}
