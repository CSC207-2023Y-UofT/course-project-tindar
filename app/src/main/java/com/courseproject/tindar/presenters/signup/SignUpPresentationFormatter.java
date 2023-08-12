package com.courseproject.tindar.presenters.signup;

import com.courseproject.tindar.usecases.signup.SignUpPresenter;

/**
 * Presentation Formatter for the Edit Profile feature
 */
public class SignUpPresentationFormatter implements SignUpPresenter {
    /**
     * prepares sign-up success view
     *
     * @param message sign-up success message
     * @return sign-up success message
     */
    @Override
    public String prepareSuccessView(String message) {
        return message;
    }

    /**
     * prepares sign-up fail view
     *
     * @param error error message
     * @return sign-up fail message
     * @throws InvalidSignUpCredentials always
     */
    @Override
    public String prepareFailView(String error) throws InvalidSignUpCredentials {
        throw new InvalidSignUpCredentials(error);
    }
}
