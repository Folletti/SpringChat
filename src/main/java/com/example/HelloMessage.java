package com.example;

public class HelloMessage {
    public HelloMessage() {
    }
    
    public HelloMessage(String name) {
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
