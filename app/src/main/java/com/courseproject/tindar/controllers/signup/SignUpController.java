package com.courseproject.tindar.controllers.signup;

import com.courseproject.tindar.presenters.signup.InvalidCredentials;
import com.courseproject.tindar.usecases.signup.SignUpInputBoundary;
import com.courseproject.tindar.usecases.signup.SignUpRequestModel;

public class SignUpController {
    private final SignUpInputBoundary userInput;

    public SignUpController(SignUpInputBoundary signUpUserInput) {
        this.userInput = signUpUserInput;
    }

    public String createAccount(SignUpRequestModel accountCredentials) throws InvalidCredentials {
        return userInput.createAccount(accountCredentials);
    }
}
