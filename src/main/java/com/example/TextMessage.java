package com.example;

public class TextMessage {
    
    public TextMessage() {}
    
    public TextMessage(String text, String sessionId) {
        this.text = text;
        this.sessionId = sessionId;
    }
    private String text;
    private String sessionId;
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
