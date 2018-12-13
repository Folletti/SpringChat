package com.example;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    public Message(String message, LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.message = message;
    }
    private LocalDateTime dateTime;
    private String message;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "dateTime=" + dateTime +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message1 = (Message) o;
        return Objects.equals(getDateTime(), message1.getDateTime()) &&
                Objects.equals(getMessage(), message1.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateTime(), getMessage());
    }
}
