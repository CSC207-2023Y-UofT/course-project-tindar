package com.courseproject.tindar.usecases.login;

public class LoginInteractor implements LoginInputBoundary {

    final LoginDsGateway loginDsGateway;

    public LoginInteractor(LoginDsGateway loginDsGateway){
        this.loginDsGateway = loginDsGateway;
    }

    @Override
    public Boolean checkUserPassword(String email, String password){
        return !(loginDsGateway.readUserId(email, password) == null);
    }

    @Override
    public String getUserId(String email, String password){
        return loginDsGateway.readUserId(email, password);
    }
}
