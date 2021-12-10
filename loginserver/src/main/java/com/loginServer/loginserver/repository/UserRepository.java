package com.loginServer.loginserver.repository;

import com.loginServer.loginserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Object / Primary key
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
