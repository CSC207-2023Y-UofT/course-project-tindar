package com.courseproject.tindar.usecases.login;

public interface LoginInputBoundary {
    Boolean checkUserPassword(String email, String password);
    String getUserId(String email, String password);
}
