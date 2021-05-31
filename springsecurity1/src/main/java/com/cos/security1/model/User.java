package com.cos.security1.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity // 테이블 생성
@Data //getter setter
public class User {
	@Id//PrimaryKey
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
	@CreationTimestamp
	private Timestamp createDate;
}
