package com.javasecurity.jwt.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO. Client로 부터 Authentication request를 받을 것
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
	private String userName;
	private String password;
}
