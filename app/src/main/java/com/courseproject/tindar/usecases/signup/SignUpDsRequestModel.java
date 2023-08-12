package com.courseproject.tindar.usecases.signup;

import lombok.Getter;

public class SignUpDsRequestModel {
    @Getter
    private final String displayName;
    @Getter private final String email;
    @Getter private final String password;

    public SignUpDsRequestModel(String displayName, String email, String password) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
    }

}
