package com.example;

public class UserNameMessage {
    public UserNameMessage() {
    }
    
    public UserNameMessage(String name) {
        this.name = name;
    }
    private String name;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
