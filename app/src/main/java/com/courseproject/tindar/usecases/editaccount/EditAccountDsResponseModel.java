package com.courseproject.tindar.usecases.editaccount;

public class EditAccountDsResponseModel {
    private final boolean isActiveStatus;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;

    public EditAccountDsResponseModel(boolean isActiveStatus, String email, String password,
                                            String firstName, String lastName) {
        this.isActiveStatus = isActiveStatus;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean getIsActiveStatus() {
        return this.isActiveStatus;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPassword() {
        return this.password;
    }
}
