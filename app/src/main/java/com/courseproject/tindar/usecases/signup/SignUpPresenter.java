package com.courseproject.tindar.usecases.signup;

import com.courseproject.tindar.presenters.signup.InvalidSignUpCredentials;

/**
 * interface for the presenter of the Sign Up feature
 */
public interface SignUpPresenter {
    /**
     * prepares sign-up success view
     *
     * @param message sign-up success message
     * @return sign-up success successMessage
     */
    String prepareSuccessView(String message);

    /**
     * prepares sign-up fail view
     *
     * @param error error message
     * @return sign-up fail message
     * @throws InvalidSignUpCredentials always
     */
    String prepareFailView(String error) throws InvalidSignUpCredentials;
}
