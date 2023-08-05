package com.courseproject.tindar.controllers.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.usecases.login.LoginDsGateway;
import com.courseproject.tindar.usecases.login.LoginInputBoundary;
import com.courseproject.tindar.usecases.login.LoginInteractor;
import com.courseproject.tindar.usecases.login.LoginInteractorUnitTest;

import org.junit.After;
import org.junit.Test;

import java.util.GregorianCalendar;

public class LoginControllerUnitTest {
    /**
     * This class tests the implementation and return values of LoginController
     **/
    public static final String email = "bell@exampleemail.com";
    public static final String password = "somepassword";

    public static final String userId = "1";

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
    LoginInputBoundary userInput = new LoginInteractor(mockLoginDsGateway);
    LoginController loginController = new LoginController(userInput);

    @Test
    public void checkCorrectUserPassword(){
        assertEquals(true, loginController.checkUserPassword(email, password));
    }

    @Test
    public void checkIncorrectUserPassword(){
        assertEquals(false, loginController.checkUserPassword(email, "password"));
    }

    @Test
    public void getCorrectUserId(){
        assertEquals(userId, loginController.getUserId(email, password));
    }

    @Test
    public void getNullUserId(){
        assertNull(loginController.getUserId(email, "password"));
    }

}
