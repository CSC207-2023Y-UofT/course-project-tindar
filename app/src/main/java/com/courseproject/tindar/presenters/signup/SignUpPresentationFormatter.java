package com.courseproject.tindar.presenters.signup;

import com.courseproject.tindar.usecases.signup.SignUpPresenter;

public class SignUpPresentationFormatter implements SignUpPresenter {
    @Override
    public String prepareSuccessView() {
        return "Account has successfully created.";
    }
    @Override
    public String prepareFailView(String error) throws InvalidCredentials {
        throw new InvalidCredentials(error);
    }
}
