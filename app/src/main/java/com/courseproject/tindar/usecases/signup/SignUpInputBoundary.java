package com.courseproject.tindar.usecases.signup;

import com.courseproject.tindar.presenters.signup.InvalidCredentials;

public interface SignUpInputBoundary {
    String createAccount(SignUpRequestModel accountCredentials) throws InvalidCredentials;
}
