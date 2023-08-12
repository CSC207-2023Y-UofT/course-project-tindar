package com.courseproject.tindar.usecases.signup;

import lombok.Getter;

/**
 * data model for the sign-up credentials for data-saving layer on sign-up request
 */
public class SignUpDsRequestModel {
    /**
     * display name of the user
     */
    @Getter
    private final String displayName;
    /**
     * email address of the user
     */
    @Getter private final String email;
    /**
     * password of the account the user creates
     */
    @Getter private final String password;

    /**
     * constructs sign-up credentials for the data-saving layer on sign-up request
     *
     * @param displayName display name of the user
     * @param email email address of the user
     * @param password password of the account the user creates
     */
    public SignUpDsRequestModel(String displayName, String email, String password) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
    }
}
