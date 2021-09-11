package org.jpwh.model.helloworld;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // TABLE
public class Message {

    @Id //ID Column
    @GeneratedValue // AUTO ID
    private Long id;
    private String text;
    private String getText(){
        return text;
    }
    private void setText(String text){
        this.text = text;
    }
}
