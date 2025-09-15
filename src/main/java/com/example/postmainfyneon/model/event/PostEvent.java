package com.example.postmainfyneon.model.event;

import lombok.Data;

@Data
public class PostEvent {
    private String content;
    private String sender;
    private String name;


    public String getName() {
        return name;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
