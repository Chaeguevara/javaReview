package com.example.h2Test.controller;

import com.example.h2Test.entity.Book;
import com.example.h2Test.entity.UserPreference;
import com.example.h2Test.entity.User;
import com.example.h2Test.repository.BookRepository;
import com.example.h2Test.repository.LikeRepository;
import com.example.h2Test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    LikeRepository likeRepository;

    @PostMapping("/user")
    public String createUser(@RequestBody User user){
        user = userRepository.save(user);
        return user.toString();
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable(value = "id") Long id){
        User user = userRepository.getById(id);
        return user.toString();
    }

    @PostMapping("/book")
    public String createBook(@RequestBody Book book){
        book = bookRepository.save(book);
        return book.toString();
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable(value = "id") Long id){
        Book book = bookRepository.getById(id);
        return book.toString();
    }

    @PostMapping("/{user-id}/like/{book-id}")
    public String createLike(@PathVariable(value = "user-id") Long userId,
                             @PathVariable(value = "book-id") Long bookId){
        UserPreference userPreference = new UserPreference();
        User user = userRepository.getById(userId);
        Book book = bookRepository.getById(bookId);
        userPreference.setBook(book);
        userPreference.setUser(user);
        userPreference.setRating(5);
        userPreference = likeRepository.save(userPreference);
        return userPreference.toString();
    }

    @GetMapping("/like/{id}")
    public String getLike(@PathVariable(value = "id") Long id){
        UserPreference userPreference = likeRepository.getById(id);
        return userPreference.toString();
    }
}
