package com.relation.h2.repository;

import com.relation.h2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface UserRepository extends JpaRepository<User,Long> {
}
