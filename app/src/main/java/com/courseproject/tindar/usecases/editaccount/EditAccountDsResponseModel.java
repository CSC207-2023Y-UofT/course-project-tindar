package com.courseproject.tindar.usecases.editaccount;

public class EditAccountDsResponseModel {
    private final boolean isActiveStatus;
    private final String email;
    private final String password;

    public EditAccountDsResponseModel(boolean isActiveStatus, String email, String password) {
        this.isActiveStatus = isActiveStatus;
        this.email = email;
        this.password = password;
    }

    public boolean getIsActiveStatus() {
        return this.isActiveStatus;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
