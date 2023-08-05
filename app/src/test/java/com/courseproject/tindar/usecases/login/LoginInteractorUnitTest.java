package com.courseproject.tindar.usecases.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class LoginInteractorUnitTest {
    /** This class tests the implementation and return values of userInput **/
    private static final String EMAIL = "bell@exampleemail.com";
    private static final String PASSWORD = "somepassword";
    private static final String USER_ID = "1";

    private static class MockLoginDsGateway implements LoginDsGateway {
        @Override
        public String readUserId(String email, String password) {
            boolean emailEqual = (email.equals(EMAIL));
            boolean passwordEqual = (password.equals(PASSWORD));

            if (emailEqual && passwordEqual){
                return USER_ID;
            } else {
                return null;
            }
        }
    }
    MockLoginDsGateway mockLoginDsGateway = new MockLoginDsGateway();
    LoginInputBoundary userInput = new LoginInteractor(mockLoginDsGateway);

    @Test
    public void checkCorrectPassword(){
        assertEquals(true, userInput.checkUserPassword(EMAIL, PASSWORD));
    }

    @Test
    public void checkWrongPassword(){
        assertEquals(false, userInput.checkUserPassword(EMAIL, "wor"));
    }

    @Test
    public void getExistingUserId(){
        assertEquals(USER_ID, userInput.getUserId(EMAIL, PASSWORD));
    }

    @Test
    public void getNullUserId(){
        assertNull(userInput.getUserId(EMAIL, "wor"));
    }
}
