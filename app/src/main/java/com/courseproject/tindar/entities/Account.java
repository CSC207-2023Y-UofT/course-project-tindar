package com.courseproject.tindar.entities;

/**
 *
 */
public class Account {
    private final String password;

    public Account(String password) {
        this.password = password;
    }

    public boolean checkIfPasswordIsValid() {
        return password.length() > 5;
    }
}
