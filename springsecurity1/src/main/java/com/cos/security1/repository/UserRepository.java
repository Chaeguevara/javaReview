package com.cos.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security1.model.User;

//Type + Primary key type
//CRUD를 JPARepo가 가짐
//@Repository없어도 됨. 왜냐면  Jpa에 포함
public interface UserRepository extends JpaRepository<User, Integer>{
	
	//findBy는 컨밴션
	//Select * from user where username= 1?
	public User findByUsername(String username); //Jpa query method

}
