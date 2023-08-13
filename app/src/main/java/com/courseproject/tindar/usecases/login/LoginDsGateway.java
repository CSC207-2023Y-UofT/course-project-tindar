package com.courseproject.tindar.usecases.login;

public interface LoginDsGateway {
    String readUserId(String email, String password);
}
