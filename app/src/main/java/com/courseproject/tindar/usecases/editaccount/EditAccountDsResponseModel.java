package com.courseproject.tindar.usecases.editaccount;
/**
 * The EditAccountDsResponseModel class represents an account for easier editing operations.
 * It holds information about the account's attributes.
 */
public class EditAccountDsResponseModel {
    private final boolean isActiveStatus;
    private final String email;
    private final String password;

    /**
     * Constructs a new EditAccountDsResponseModel object with the provided attributes.
     *
     * @param isActiveStatus The active status of the account. True if active; false otherwise.
     * @param email          The email address of the account.
     * @param password       The password of the account.
     */

    public EditAccountDsResponseModel(boolean isActiveStatus, String email, String password) {
        this.isActiveStatus = isActiveStatus;
        this.email = email;
        this.password = password;
    }

    /**
     * Retrieves the active status of the account.
     *
     * @return The active status value. True if active; false otherwise.
     */
    public boolean getIsActiveStatus() {
        return this.isActiveStatus;
    }

    /**
     * Retrieves the email address of the account.
     *
     * @return The email address.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Retrieves the password of the account.
     *
     * @return The password.
     */
    public String getPassword() {
        return this.password;
    }
}
