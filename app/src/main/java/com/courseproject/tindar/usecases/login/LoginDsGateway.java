package com.courseproject.tindar.usecases.login;

public interface LoginDsGateway {
    public String readUserId(String email, String password);
}
