package com.example.h2Test.repository;


import com.example.h2Test.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<UserPreference,Long> {
}
