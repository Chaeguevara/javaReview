package com.example.domain.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateUserRequest {
	@NotBlank @Email
	private String username;
	@NotBlank
	private String fullName;
	@NotBlank
	private String password;
	@NotBlank
	private String rePassWord;
	private Set<String> authorities;
}
