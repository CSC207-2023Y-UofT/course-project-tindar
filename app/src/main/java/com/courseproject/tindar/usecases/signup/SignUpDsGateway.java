package com.courseproject.tindar.usecases.signup;

/**
 * interface for the data-saving gateway of Sign Up feature
 */
public interface SignUpDsGateway {
    /**
     * checks if the provided email address is already used by another user
     *
     * @param email email address of the user
     * @return true if the provided email address is already used by another user; false otherwise
     */
    boolean checkIfEmailAlreadyUsed(String email);

    /**
     * creates an account with the provided sign-up credentials
     *
     * @param signUpDsRequestModel provided sign-up credentials
     * @return the user id of the newly created account
     */
    String addAccount(SignUpDsRequestModel signUpDsRequestModel);
}
