package com.courseproject.tindar.controllers.signup;

import com.courseproject.tindar.presenters.signup.InvalidSignUpCredentials;
import com.courseproject.tindar.usecases.signup.SignUpInputBoundary;
import com.courseproject.tindar.usecases.signup.SignUpRequestModel;

/**
 * controller for the Sign Up UI
 */
public class SignUpController {
    /**
     * interactor of the Sign Up feature
     */
    private final SignUpInputBoundary userInput;

    /**
     * constructs controller for the Sign Up feature
     *
     * @param signUpUserInput interactor of the Sign Up feature
     */
    public SignUpController(SignUpInputBoundary signUpUserInput) {
        this.userInput = signUpUserInput;
    }

    /**
     * creates a new account with the provided sign-up credentials
     *
     * @param signUpCredentials provided sign-up credentials. The credentials include display name, email, password,
     *                          and retyped password
     * @return user id of the newly created account
     * @throws InvalidSignUpCredentials if sign-up credentials provided is invalid
     */
    public String createAccount(SignUpRequestModel signUpCredentials) throws InvalidSignUpCredentials {
        return userInput.createAccount(signUpCredentials);
    }
}
