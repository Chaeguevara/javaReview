package com.example.domain.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;

@Data
public class SearchAuthorsQuery {
	private String id;
	
	private String creatorId;
	private LocalDateTime createdAtStart;
	private LocalDateTime createdAtEnd;
	
	private String fullName;
	private Set<String> genres;
	
	private String bookId;
	private String bookTitle;
}
