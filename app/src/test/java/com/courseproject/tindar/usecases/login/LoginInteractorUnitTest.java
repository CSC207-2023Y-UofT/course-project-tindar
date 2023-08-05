package com.courseproject.tindar.usecases.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class LoginInteractorUnitTest {
    /** This class tests the implementation and return values of LoginInteractor **/
    private static final String email = "bell@exampleemail.com";
    private static final String password = "somepassword";
    private static final String userId = "1";

    private static class MockLoginDsGateway implements LoginDsGateway {
        private static final String email = "bell@exampleemail.com";
        private static final String password = "somepassword";
        private static final String userId = "1";

        public MockLoginDsGateway(){}

        @Override
        public String readUserId(String email, String password) {
            boolean emailEqual = (email.equals(this.email));
            boolean passwordEqual = (password.equals(this.password));

            if (emailEqual && passwordEqual){
                return this.userId;
            } else {
                return null;
            }
        }
    }
    MockLoginDsGateway mockLoginDsGateway = new MockLoginDsGateway();
    LoginInteractor loginInteractor = new LoginInteractor(mockLoginDsGateway);

    @Test
    public void checkCorrectPassword(){
        assertEquals(true, loginInteractor.checkUserPassword(this.email, this.password));
    }

    @Test
    public void checkWrongPassword(){
        assertEquals(false, loginInteractor.checkUserPassword(this.email, "wor"));
    }

    @Test
    public void getExistingUserId(){
        assertEquals(this.userId, loginInteractor.getUserId(this.email, this.password));
    }

    @Test
    public void getNullUserId(){
        assertNull(loginInteractor.getUserId(this.email, "wor"));
    }
}
