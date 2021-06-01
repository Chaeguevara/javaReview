package com.cos.security1.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 테이블 생성
@Data //getter setter
@NoArgsConstructor //DefaultConstructor
public class User {
	@Id//PrimaryKey
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
	
	private String provider; // google
	private String providerId; // sub...
	@CreationTimestamp
	private Timestamp createDate;
	
	@Builder //OAuth2로 가입할때	 
	public User(String username, String password, String email, String role, String provider, String providerId,
			Timestamp createDate) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
		this.createDate = createDate;
	}
	
	
}
