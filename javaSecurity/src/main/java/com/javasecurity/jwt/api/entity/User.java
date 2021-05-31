package com.javasecurity.jwt.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok
@AllArgsConstructor // 모든 아규먼트를 받는 컨스트럭터
@NoArgsConstructor // 빈 컨스트럭터
@Entity // DB에 쓴다는 뜻
@Table (name = "USER_TBL") // DB중 "USER_TBL" 테이블에 해당한다
public class User {
	
	
	@Id //PK
	private int id;
	private String userName;
	private String password;
	private String email;
	
}
