package com.example.domain.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateUserRequest {
	@NotBlank
	private String fullName;
	private Set<String> authorities;
}
