package com.courseproject.tindar.entities;

import java.util.ArrayList;
import java.util.Date;

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
