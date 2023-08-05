package com.courseproject.tindar.presenters.signup;

public class InvalidCredentials extends Exception {
    public InvalidCredentials(String error) {
        super(error);
    }
}
