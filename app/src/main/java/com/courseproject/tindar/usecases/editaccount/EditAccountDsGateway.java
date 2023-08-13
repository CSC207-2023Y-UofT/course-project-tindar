package com.courseproject.tindar.usecases.editaccount;

/**
 * The EditAccountDsGateway interface defines methods for interacting with the data source
 * to perform account editing operations.
 */
public interface EditAccountDsGateway {
    /**
     * Reads the account information associated with the specified user ID.
     *
     * @param userId The ID of the user account to be read.
     * @return An EditAccountDsResponseModel containing the account information.
     */
    EditAccountDsResponseModel readAccount(String userId);

    /**
     * Updates the active status of the user account.
     *
     * @param userId         The ID of the user account to be updated.
     * @param isActiveStatus The new active status value. True if active; false otherwise.
     */
    void updateIsActiveStatus(String userId, boolean isActiveStatus);

    /**
     * Updates the email address of the user account.
     *
     * @param userId The ID of the user account to be updated.
     * @param email  The new email address.
     * @return True if the email address was successfully updated, false otherwise.
     */
    boolean updateEmail(String userId, String email);

    /**
     * Updates the password of the user account.
     *
     * @param userId   The ID of the user account to be updated.
     * @param password The new password.
     * @return True if the password was successfully updated, false otherwise.
     */
    boolean updatePassword(String userId, String password);
}
