package com.example.domain.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EditAuthorRequest {
	@NotNull
	private String fullName;
	private String about;
	private String nationality;
	private List<String> genres;
}
