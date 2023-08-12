package com.courseproject.tindar.entities;

/**
 * (app) account entity
 */
public class Account {
    /**
     * password of the account
     */
    private final String password;

    /**
     * constructs Account entity
     *
     * @param password password of the account
     */
    public Account(String password) {
        this.password = password;
    }

    /**
     * checks if the provided password is valid
     *
     * @return true if password is valid; false otherwise.
     */
    public boolean checkIfPasswordIsValid() {
        return password.length() > 5;
    }
}
