package com.example;

public class Greeting {
    public Greeting(String content, String forUser) {
        this.content = content;
        this.forUser = forUser;
    }
    private String content;
    private String forUser;
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getForUser() {
        return forUser;
    }
    
    public void setForUser(String forUser) {
        this.forUser = forUser;
    }
}
