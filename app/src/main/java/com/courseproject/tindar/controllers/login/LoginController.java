package com.courseproject.tindar.controllers.login;

import com.courseproject.tindar.usecases.login.LoginDsGateway;
import com.courseproject.tindar.usecases.login.LoginInputBoundary;
import com.courseproject.tindar.usecases.login.LoginInteractor;

public class LoginController {

    final LoginInteractor loginInteractor;

    public LoginController(LoginInteractor loginInteractor){
        this.loginInteractor = loginInteractor;
    }

    public Boolean checkUserPassword(String email, String password){
        return loginInteractor.checkUserPassword(email, password);
    }

    public String getUserId(String email, String password){
        return loginInteractor.getUserId(email, password);
    }
}
