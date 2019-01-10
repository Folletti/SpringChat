package com.example;

public class OutputMessage {
    public OutputMessage() {}
    public OutputMessage(String content) {
        this.content = content;
    }
    
    private String content;
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
}
