package com.example.h2Test.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;
    private int rating;
}
