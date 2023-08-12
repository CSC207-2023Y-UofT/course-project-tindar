package com.courseproject.tindar.controllers.login;

import com.courseproject.tindar.usecases.login.LoginInputBoundary;

public class LoginController {

    final LoginInputBoundary userInput;

    public LoginController(LoginInputBoundary loginUserInput){
        this.userInput = loginUserInput;
    }

    /**
     * Checks if the password the user inputted for the email is the correct
     * password associated with that email in the database.
     *
     * @param email The email inputted by the user.
     * @param password The password inputted by the user.
     * @return The boolean result of checking if the password matches.
     */
    public Boolean checkUserPassword(String email, String password){
        return userInput.checkUserPassword(email, password);
    }

    /**
     * Checks if the userId associated with the inputted email.
     * This only gets called when the inputted email and password are correct.
     *
     * @param email The email inputted by the user.
     * @param password The password inputted by the user.
     * @return The userId with the associated email and password.
     */
    public String getUserId(String email, String password){
        return userInput.getUserId(email, password);
    }
}
