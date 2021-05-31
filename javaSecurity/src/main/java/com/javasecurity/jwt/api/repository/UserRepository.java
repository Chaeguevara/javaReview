package com.javasecurity.jwt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javasecurity.jwt.api.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> /* Entity + Primary Key*/ {

	User findByUserName(String username);

}
