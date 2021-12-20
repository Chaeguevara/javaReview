package com.example.h2Test.repository;


import com.example.h2Test.entity.Book;
import com.example.h2Test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
