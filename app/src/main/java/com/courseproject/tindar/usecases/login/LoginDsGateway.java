package com.courseproject.tindar.usecases.login;

/**
 * This interface defines the gateway for accessing the data source related to user login
 * information.
 * It provides methods to read user IDs based on their email and password.
 */
public interface LoginDsGateway {

    /**
     * Retrieves the user ID associated with the given email and password.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @return The user ID if the email and password match; otherwise, null.
     */
    String readUserId(String email, String password);
}
