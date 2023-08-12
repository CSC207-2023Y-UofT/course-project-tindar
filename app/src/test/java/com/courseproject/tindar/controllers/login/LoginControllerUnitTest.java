package com.courseproject.tindar.controllers.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.courseproject.tindar.usecases.login.LoginDsGateway;
import com.courseproject.tindar.usecases.login.LoginInputBoundary;
import com.courseproject.tindar.usecases.login.LoginInteractor;

import org.junit.Test;

public class LoginControllerUnitTest {
    /**
     * This class tests the implementation and return values of LoginController
     **/
    public static final String EMAIL= "bell@exampleemail.com";
    public static final String PASSWORD = "somepassword";

    public static final String USER_ID = "1";

    private static class MockLoginDsGateway implements LoginDsGateway {
        public MockLoginDsGateway(){}

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

    final MockLoginDsGateway mockLoginDsGateway = new MockLoginDsGateway();
    final LoginInputBoundary userInput = new LoginInteractor(mockLoginDsGateway);
    final LoginController loginController = new LoginController(userInput);

    @Test
    public void checkCorrectUserPassword(){
        assertEquals(true, loginController.checkUserPassword(EMAIL, PASSWORD));
    }

    @Test
    public void checkIncorrectUserPassword(){
        assertEquals(false, loginController.checkUserPassword(EMAIL, "password"));
    }

    @Test
    public void getCorrectUserId(){
        assertEquals(USER_ID, loginController.getUserId(EMAIL, PASSWORD));
    }

    @Test
    public void getNullUserId(){
        assertNull(loginController.getUserId(EMAIL, "password"));
    }

}
