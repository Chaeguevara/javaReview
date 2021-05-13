package com.example.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AuthRequest {
	
	@NotNull @Email
	private String userName;
	@NotNull
	private String password;
}
