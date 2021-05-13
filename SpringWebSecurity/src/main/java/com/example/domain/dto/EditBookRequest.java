package com.example.domain.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

public class EditBookRequest {
	private List<@NotNull String> authorIds;
	
	@NotNull
	private String title;
	private String about;
	private String language;
	private List<String> genres;
	private String isbn13;
	private String isbn10;
	private String publisher;
	private LocalDate publishDate;
	private Integer hardcover;
}
