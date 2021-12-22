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

import java.util.List;


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
        System.out.println("createUser");
        user = userRepository.save(user);
        UserPreference userPreference = new UserPreference();
        if(user.getUserPreferenceList()!=null){
            System.out.println(user.getUserPreferenceList().get(0).toString());
        }
        return user.toString();
    }

    @GetMapping("/user")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable(value = "id") Long id){
        User user = userRepository.getById(id);
        return user;
    }

    @PostMapping("/book")
    public Book createBook(@RequestBody Book book){
        book = bookRepository.save(book);
        return book;
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable(value = "id") Long id){
        Book book = bookRepository.getById(id);
        return book;
    }

    @PostMapping("/{user-id}/like/{book-id}")
    public UserPreference createLike(@PathVariable(value = "user-id") Long userId,
                             @PathVariable(value = "book-id") Long bookId){
        User user = userRepository.getById(userId);
        Book book = bookRepository.getById(bookId);
        UserPreference userPreference = new UserPreference();
        userPreference.setBook(book);
        userPreference.setUser(user);
        userPreference.setRating(5);
        userPreference = likeRepository.save(userPreference);
        return userPreference;
    }

    @GetMapping("/like/{id}")
    public UserPreference getLike(@PathVariable(value = "id") Long id){
        UserPreference userPreference = likeRepository.getById(id);
        return userPreference;
    }

    @GetMapping("/like")
    public List<UserPreference> getAllLike(){
        List<UserPreference> userPreferenceList =  likeRepository.findAll();
        return userPreferenceList;
    }

    @PostMapping("/like")
    public UserPreference createPreference(){
        User user = userRepository.getById(Integer.toUnsignedLong(1));
        Book book = bookRepository.getById(Integer.toUnsignedLong(1));

        UserPreference userPreference = new UserPreference();
        userPreference.setRating(5);
        userPreference.setUser(user);
        userPreference.setBook(book);
        userPreference = likeRepository.save(userPreference);
        return userPreference;
    }
}
