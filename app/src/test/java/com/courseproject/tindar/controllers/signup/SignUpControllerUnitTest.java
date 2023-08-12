package com.courseproject.tindar.controllers.signup;

import static org.junit.Assert.assertEquals;

import com.courseproject.tindar.presenters.signup.InvalidCredentials;
import com.courseproject.tindar.usecases.signup.SignUpInputBoundary;
import com.courseproject.tindar.usecases.signup.SignUpRequestModel;

import org.junit.Test;

public class SignUpControllerUnitTest {

    private final String SUCCESS_MESSAGE = "created!";

    private class MockSignUpUserInput implements SignUpInputBoundary {

        @Override
        public String createAccount(SignUpRequestModel accountCredentials) {
            return SUCCESS_MESSAGE;
        }
    }

    @Test
    public void testCreateAccount() throws InvalidCredentials {
        SignUpInputBoundary signUpUserInput = new MockSignUpUserInput();
        SignUpController signUpController = new SignUpController(signUpUserInput);
        SignUpRequestModel accountCredentials = new SignUpRequestModel("bell", "bell@someemail.com", "password1",
                "password1");
        assertEquals(SUCCESS_MESSAGE, signUpController.createAccount(accountCredentials));
    }
}
