package com.courseproject.tindar.usecases.signup;

import lombok.Getter;

/**
 *
 */
public class SignUpRequestModel {
    @Getter private final String displayName;
    @Getter private final String email;
    @Getter private final String password;
    @Getter private final String retypedPassword;

    public SignUpRequestModel(String displayName, String email, String password, String retypedPassword) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.retypedPassword = retypedPassword;
    }
}
