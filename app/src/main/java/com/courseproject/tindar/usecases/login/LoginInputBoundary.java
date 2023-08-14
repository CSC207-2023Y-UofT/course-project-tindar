package com.courseproject.tindar.usecases.login;

/**
 * This interface defines the input boundary for the user login use case.
 * It provides methods for checking user credentials and retrieving user IDs based on email
 * and password.
 */
public interface LoginInputBoundary {

    /**
     * Checks whether the provided email and password match a user's credentials.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @return boolean  True if the provided credentials are valid, otherwise
     */
    Boolean checkUserPassword(String email, String password);

    /**
     * Retrieves the user ID associated with the given email and password.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @return The user ID if the email and password are valid, otherwise null
     */
    String getUserId(String email, String password);
}
