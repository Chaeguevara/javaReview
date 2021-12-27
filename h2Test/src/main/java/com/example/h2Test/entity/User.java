package com.example.h2Test.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Entity
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
//    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserPreference> userPreferenceList;

    @Transient
    public List<UserPreference> getUserPreferenceList(){
        if (userPreferenceList ==null || userPreferenceList.size()==0) return new ArrayList<>();
        Iterator<UserPreference> iterator = userPreferenceList.iterator();
//        while(iterator.hasNext()){
//            UserPreference userPreference = iterator.next()
//        }
        return userPreferenceList;
    }

}
