package com.courseproject.tindar.entities;

/**
 * factory class to create Account entity
 */
public class AccountFactory {
    /**
     * creates account entity with provided password
     *
     * @param password password of the account
     * @return Account entity
     */
    public Account create(String password) {
        return new Account(password);
    }
}
