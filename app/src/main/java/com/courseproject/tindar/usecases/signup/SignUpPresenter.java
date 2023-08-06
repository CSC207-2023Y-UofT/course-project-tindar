package com.courseproject.tindar.usecases.signup;

import com.courseproject.tindar.presenters.signup.InvalidCredentials;

public interface SignUpPresenter {
    String prepareSuccessView();
    String prepareFailView(String error) throws InvalidCredentials;
}
