package com.example.polls.model.audit;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
@Getter
@Setter
public abstract class DateAudit implements Serializable {

	// We’ll use JPA’s AuditingEntityListener to automatically
	// populate createdAt and updatedAt values when we persist an entity.

	// database auditing means tracking and logging events related to persistent
	// entities, or simply entity versioning
	
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private Instant createdAt;
	
	@LastModifiedDate
	@Column(nullable = false)
	private Instant updatedAt;

}
