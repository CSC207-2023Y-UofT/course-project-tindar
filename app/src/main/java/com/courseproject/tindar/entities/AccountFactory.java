package com.courseproject.tindar.entities;

public class AccountFactory {
    public Account create(String password) {
        return new Account(password);
    }
}
