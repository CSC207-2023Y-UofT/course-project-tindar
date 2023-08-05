package com.courseproject.tindar.usecases.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class LoginInteractorUnitTest {
    /** This class tests the implementation and return values of userInput **/
    private static final String EMAIL = "bell@exampleemail.com";
    private static final String PASSWORD = "somepassword";
    private static final String USERID = "1";

    private static class MockLoginDsGateway implements LoginDsGateway {
        private static final String EMAIL = "bell@exampleemail.com";
        private static final String PASSWORD = "somepassword";
        private static final String USERID = "1";

        @Override
        public String readUserId(String email, String password) {
            boolean emailEqual = (email.equals(this.EMAIL));
            boolean passwordEqual = (password.equals(this.PASSWORD));

            if (emailEqual && passwordEqual){
                return this.USERID;
            } else {
                return null;
            }
        }
    }
    MockLoginDsGateway mockLoginDsGateway = new MockLoginDsGateway();
    LoginInputBoundary userInput = new LoginInteractor(mockLoginDsGateway);

    @Test
    public void checkCorrectPassword(){
        assertEquals(true, userInput.checkUserPassword(this.EMAIL, this.PASSWORD));
    }

    @Test
    public void checkWrongPassword(){
        assertEquals(false, userInput.checkUserPassword(this.EMAIL, "wor"));
    }

    @Test
    public void getExistingUserId(){
        assertEquals(this.USERID, userInput.getUserId(this.EMAIL, this.PASSWORD));
    }

    @Test
    public void getNullUserId(){
        assertNull(userInput.getUserId(this.EMAIL, "wor"));
    }
}
