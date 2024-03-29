package org.jpwh.model.helloworld;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue //Auto ID generation
    private Long id;

    private String text;

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }
}
