package com.courseproject.tindar.usecases.signup;

import lombok.Getter;

/**
 * data model for the sign-up credentials on sign-up request
 */
public class SignUpRequestModel {
    /**
     * display name of the user
     */
    @Getter private final String displayName;
    /**
     * email address of the user
     */
    @Getter private final String email;
    /**
     * password of the account the user creates
     */
    @Getter private final String password;
    /**
     * retyped password of the account the user creates for confirmation
     */
    @Getter private final String retypedPassword;

    /**
     * constructs sign-up credentials on sign-up request
     *
     * @param displayName display name of the user
     * @param email email address of the user
     * @param password password of the account the user creates
     * @param retypedPassword retyped password of the account the user creates for confirmation
     */
    public SignUpRequestModel(String displayName, String email, String password, String retypedPassword) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.retypedPassword = retypedPassword;
    }
}
