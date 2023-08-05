package com.courseproject.tindar.controllers.login;

import com.courseproject.tindar.usecases.login.LoginDsGateway;
import com.courseproject.tindar.usecases.login.LoginInputBoundary;
import com.courseproject.tindar.usecases.login.LoginInteractor;

public class LoginController {

    final LoginInputBoundary userInput;

    public LoginController(LoginInputBoundary loginUserInput){
        this.userInput = loginUserInput;
    }

    public Boolean checkUserPassword(String email, String password){
        return userInput.checkUserPassword(email, password);
    }

    public String getUserId(String email, String password){
        return userInput.getUserId(email, password);
    }
}
