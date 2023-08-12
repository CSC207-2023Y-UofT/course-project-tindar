package com.courseproject.tindar.presenters.signup;

/**
 * invalid sign-up credentials exception. Subclass of Exception
 */
public class InvalidSignUpCredentials extends Exception {
    /**
     * constructs invalid sign-up credentials exception
     *
     * @param error error message
     */
    public InvalidSignUpCredentials(String error) {
        super(error);
    }
}
