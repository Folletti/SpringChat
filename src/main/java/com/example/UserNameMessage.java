package com.example;

public class UserNameMessage {
    public UserNameMessage() {
    }
    
    public UserNameMessage(String name, String sessionId) {
        this.name = name;
        this.sessionId = sessionId;
    }
    private String name;
    private String sessionId;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
