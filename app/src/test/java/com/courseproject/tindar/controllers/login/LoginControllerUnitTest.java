package com.courseproject.tindar.controllers.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.usecases.login.LoginDsGateway;
import com.courseproject.tindar.usecases.login.LoginInteractor;

import org.junit.After;
import org.junit.Test;

import java.util.GregorianCalendar;

public class LoginControllerUnitTest {
    /**
     * This class tests the implementation and return values of LoginController
     **/
    public static final String email = "bell@exampleemail.com";
    public static final String password = "somepassword";

    private DatabaseHelper dbHelper;
    private String userId;
    private LoginDsGateway loginDatabaseHelper;
    private LoginInteractor loginInteractor;
    private LoginController loginController;

    public void setUp() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dbHelper = DatabaseHelper.getInstance(appContext);
        userId = dbHelper.addAccount(true, "bell@exampleemail.com", "somepassword", "bell",
                "Bell", "Robin", new GregorianCalendar(2003, 9, 5).getTime(),
                "Female", "Calgary", "https://ccc", "I would like to",
                "Female, Male", "Calgary, Vancouver", 19, 999);
        loginDatabaseHelper = DatabaseHelper.getInstance(appContext);
        loginInteractor = new LoginInteractor(loginDatabaseHelper);
        loginController = new LoginController(loginInteractor);
    }

    @After
    public void tearDown() {
        dbHelper.close();
    }


    @Test
    public void checkCorrectUserPassword(){
        assertEquals(true, loginController.checkUserPassword(email, password));
    }

    @Test
    public void checkIncorrectUserPassword(){
        assertEquals(true, loginController.checkUserPassword(email, "password"));
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
