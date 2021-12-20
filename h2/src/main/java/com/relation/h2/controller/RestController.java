package com.relation.h2.controller;

import com.relation.h2.entity.User;
import com.relation.h2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.RestController
@Service
public class RestController {
    @Autowired
    UserRepository userRepository;


    @PostMapping("/user")
    public String createUser(@RequestBody User user){
        user = userRepository.save(user);
        return user.toString();
    }
}
