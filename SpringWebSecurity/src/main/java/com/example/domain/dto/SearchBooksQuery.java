package com.example.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;

@Data
public class SearchBooksQuery {
	private String id;
	
	private String creatorId;
	private LocalDateTime createdAtStart;
	private LocalDateTime createdAtEnd;
	
	private String title;
	private Set<String> genre;
	private String isbn13;
	private String isbn10;
	private String publisher;
	private LocalDate publishDateStart;
	private LocalDate publishDateEnd;
	
	private String authorId;
	private String authorFullName;
}
