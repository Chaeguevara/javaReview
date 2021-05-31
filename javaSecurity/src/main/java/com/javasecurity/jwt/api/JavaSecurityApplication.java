package com.javasecurity.jwt.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javasecurity.jwt.api.entity.User;
import com.javasecurity.jwt.api.repository.UserRepository;
import com.javasecurity.jwt.api.repository.UserRepository;
import com.javasecurity.jwt.api.repository.UserRepository;

@SpringBootApplication
public class JavaSecurityApplication {
	
	// inituser의 user들을 inject 
	@Autowired 
	private UserRepository repository;
	
	// 앱이 시작할때 만들어지도록
	@PostConstruct
	public void initUser() {
        List<User> users = Stream.of(
                new User(101, "javatechie", "password", "javatechie@gmail.com"),
                new User(102, "user1", "pwd1", "user1@gmail.com"),
                new User(103, "user2", "pwd2", "user2@gmail.com"),
                new User(104, "user3", "pwd3", "user3@gmail.com")
        ).collect(Collectors.toList());
        //repository에 저장
        repository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaSecurityApplication.class, args);
	}

}
