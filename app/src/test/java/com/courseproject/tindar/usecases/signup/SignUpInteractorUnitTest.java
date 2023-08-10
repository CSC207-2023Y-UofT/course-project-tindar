package com.courseproject.tindar.usecases.signup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.courseproject.tindar.entities.AccountFactory;
import com.courseproject.tindar.presenters.signup.InvalidCredentials;
import com.courseproject.tindar.presenters.signup.SignUpPresentationFormatter;

import org.junit.Test;

import java.util.Objects;

public class SignUpInteractorUnitTest {
    private static final String USER_ID = "99";
    private static final String DISPLAY_NAME = "bell";
    private static final String EMAIL = "bell@sometestemail.com";
    private static final String PASSWORD = "bell_password";
    private static final String EMAIL_ALREADY_IN_USE = "emailalreadyinuse@sometestemail.com";

    private class MockSignUpDsGateway implements SignUpDsGateway {

        private final String createdUserId;
        private final String email;
        private final String password;

        public MockSignUpDsGateway(String createdUserId, String email, String password) {
            this.createdUserId = createdUserId;
            this.email = email;
            this.password = password;
        }

        @Override
        public boolean checkIfEmailAlreadyUsed(String email) {
            return Objects.equals(email, EMAIL_ALREADY_IN_USE);
        }

        @Override
        public String addAccount(SignUpDsRequestModel signUpDsRequestModel) {
            assertEquals(DISPLAY_NAME, signUpDsRequestModel.getDisplayName());
            assertEquals(email, signUpDsRequestModel.getEmail());
            assertEquals(password, signUpDsRequestModel.getPassword());
            return createdUserId;
        }
    }

    SignUpPresenter signUpsPresentationFormatter = new SignUpPresentationFormatter();
    AccountFactory accountFactory = new AccountFactory();

    @Test
    public void testCreateAccountSuccess() throws InvalidCredentials {
        SignUpDsGateway mockSignUpDsGateway = new MockSignUpDsGateway(USER_ID, EMAIL, PASSWORD);
        SignUpInteractor testSignUpInteractor = new SignUpInteractor(mockSignUpDsGateway,
                signUpsPresentationFormatter, accountFactory);
        SignUpRequestModel accountCredentials = new SignUpRequestModel(DISPLAY_NAME, EMAIL, PASSWORD, PASSWORD);
        String successMessage = testSignUpInteractor.createAccount(accountCredentials);
        assertEquals("Account has successfully created.", successMessage);
    }

    @Test
    public void testCreateAccountPasswordDoNotMatch() {
        SignUpDsGateway mockSignUpDsGateway = new MockSignUpDsGateway(USER_ID, EMAIL, PASSWORD);
        SignUpInteractor testSignUpInteractor = new SignUpInteractor(mockSignUpDsGateway,
                signUpsPresentationFormatter, accountFactory);
        SignUpRequestModel accountCredentials = new SignUpRequestModel(DISPLAY_NAME, EMAIL, PASSWORD,
                "some_other_password");
        Exception exception = assertThrows(InvalidCredentials.class,
                () -> testSignUpInteractor.createAccount(accountCredentials));
        assertEquals("Password and re-typed password do not match.", exception.getMessage());
    }

    @Test
    public void testCreateAccountPasswordInvalid() {
        SignUpDsGateway mockSignUpDsGateway = new MockSignUpDsGateway(USER_ID, EMAIL, "short");
        SignUpInteractor testSignUpInteractor = new SignUpInteractor(mockSignUpDsGateway,
                signUpsPresentationFormatter, accountFactory);
        SignUpRequestModel accountCredentials = new SignUpRequestModel(DISPLAY_NAME, EMAIL, "short",
                "short");
        Exception exception = assertThrows(InvalidCredentials.class,
                () -> testSignUpInteractor.createAccount(accountCredentials));
        assertEquals("Invalid password. The password has to be at least 6 characters long.", exception.getMessage());
    }

    @Test
    public void testCreateAccountEmailAlreadyInUse() {
        SignUpDsGateway mockSignUpDsGateway = new MockSignUpDsGateway(USER_ID, EMAIL_ALREADY_IN_USE, PASSWORD);
        SignUpInteractor testSignUpInteractor = new SignUpInteractor(mockSignUpDsGateway,
                signUpsPresentationFormatter, accountFactory);
        SignUpRequestModel accountCredentials = new SignUpRequestModel(DISPLAY_NAME, EMAIL_ALREADY_IN_USE, PASSWORD,
                PASSWORD);
        Exception exception = assertThrows(InvalidCredentials.class,
                () -> testSignUpInteractor.createAccount(accountCredentials));
        assertEquals("Email is already in use.", exception.getMessage());
    }

    @Test
    public void testCreateAccountUnknownError() {
        SignUpDsGateway mockSignUpDsGateway = new MockSignUpDsGateway("-1", EMAIL, PASSWORD);
        SignUpInteractor testSignUpInteractor = new SignUpInteractor(mockSignUpDsGateway,
                signUpsPresentationFormatter, accountFactory);
        SignUpRequestModel accountCredentials = new SignUpRequestModel(DISPLAY_NAME, EMAIL, PASSWORD, PASSWORD);
        Exception exception = assertThrows(InvalidCredentials.class,
                () -> testSignUpInteractor.createAccount(accountCredentials));
        assertEquals("Unknown error. Fail to create an account. Please try it again.", exception.getMessage());
    }
}

