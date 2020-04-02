package com.example.polls;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;

@SpringBootApplication
@EntityScan(basePackageClasses = { CallicoderPollsApplication.class, Jsr310Converters.class })
public class CallicoderPollsApplication {
	
	// Register JPA 2.1 converters so that all the Java 8 Date/Time fields in the domain models automatically 
	// get converted to SQL types when we persist them in the database.
	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(CallicoderPollsApplication.class, args);
	}

}
