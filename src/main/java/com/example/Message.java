package com.example;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;



public class Message {
    private static DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy HH:mm:ss");

    public Message(User user, String message, Instant dateTime) {
        this.dateTime = dateTime;
        this.message = message;
        this.user = user;
    }
    private Instant dateTime;
    private String message;
    private User user;

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "dateTime=" + dateTime +
                ", message='" + message + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message1 = (Message) o;
        return Objects.equals(getDateTime(), message1.getDateTime()) &&
                Objects.equals(getMessage(), message1.getMessage()) &&
                Objects.equals(getUser(), message1.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateTime(), getMessage(), getUser());
    }

    public String getFormattedMessage() {
        return dateTime.atZone(ZoneId.of("Europe/Samara")).format(formatter) +
                " | " + user.getFullName()+ ": " + message;
    }
}
