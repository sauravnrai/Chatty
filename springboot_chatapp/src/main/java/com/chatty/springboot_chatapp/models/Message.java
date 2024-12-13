package com.chatty.springboot_chatapp.models;

public class Message {

    private String name;
    private String content;

    
  // Constructor for the class members
    public Message(String name, String content) {
        this.name = name;
        this.content = content;
    }


    // Getters and Setters 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    

    

}
