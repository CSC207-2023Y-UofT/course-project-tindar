package com.courseproject.tindar.usecases.signup;

import com.courseproject.tindar.presenters.signup.InvalidSignUpCredentials;

/**
 * interface for the interactor of the Sign Up feature
 */
public interface SignUpInputBoundary {
    /**
     * creates a new account with the provided sign-up credentials
     *
     * @param signUpCredentials provided sign-up credentials. The credentials include display name, email, password
     *                          and retyped password
     * @return success or fail message on account creation
     * @throws InvalidSignUpCredentials if the provided sign-up credentials is invalid
     */
    String createAccount(SignUpRequestModel signUpCredentials) throws InvalidSignUpCredentials;
}
