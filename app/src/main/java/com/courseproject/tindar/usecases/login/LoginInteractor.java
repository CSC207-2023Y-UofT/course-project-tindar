package com.courseproject.tindar.usecases.login;

/**
 * This class implements the login use case's input boundary.
 * It interacts with the data source gateway to authenticate users and retrieve user IDs.
 */
public class LoginInteractor implements LoginInputBoundary {

    final LoginDsGateway loginDsGateway;

    /**
     * Constructs a new LoginInteractor with the specified LoginDsGateway.
     *
     * @param loginDsGateway The data source gateway for login-related operations.
     */
    public LoginInteractor(LoginDsGateway loginDsGateway){
        this.loginDsGateway = loginDsGateway;
    }

    /**
     * Checks whether the provided email and password match a user's credentials.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @return Boolean if the provided credentials are valid, otherwise false
     */
    @Override
    public Boolean checkUserPassword(String email, String password){
        return !(loginDsGateway.readUserId(email, password) == null);
    }

    /**
     * Retrieves the user ID associated with the given email and password.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @return The user ID if the email and password are valid, otherwise null
     */
    @Override
    public String getUserId(String email, String password){
        return loginDsGateway.readUserId(email, password);
    }
}
