package com.example;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;



public class Message {
    public Message() {}
    
    private static DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy HH:mm:ss");

    public Message(User user, String text, Instant dateTime) {
        this.dateTime = dateTime;
        this.text = text;
        this.user = user;
    }
    private Instant dateTime;
    private String text;
    private User user;

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message1 = (Message) o;
        return Objects.equals(getDateTime(), message1.getDateTime()) &&
                Objects.equals(getText(), message1.getText()) &&
                Objects.equals(getUser(), message1.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateTime(), getText(), getUser());
    }

    public String getFormattedMessage() {
        return dateTime.atZone(ZoneId.of("Europe/Samara")).format(formatter) + " | " + user.getFullName()+ ": " + text;
    }
}
