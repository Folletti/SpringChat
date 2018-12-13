package com.example;

import java.util.Objects;

public class User {
    public User(String fullName) {
        this.fullName = fullName;
    }

    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getFullName(), user.getFullName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName());
    }
}
