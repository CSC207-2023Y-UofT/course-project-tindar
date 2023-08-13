package com.courseproject.tindar.usecases.editaccount;

/**
 * The EditAccountInputBoundary interface defines methods for interacting with the application layer
 * to perform account editing operations and retrieve account information.
 */
public interface EditAccountInputBoundary {
    /**
     * Retrieves the account information associated with the specified user ID.
     *
     * @param userId The ID of the user account to retrieve.
     * @return An EditAccountDsResponseModel containing the account information.
     */
    EditAccountDsResponseModel getAccount(String userId);

    /**
     * Updates the active status of the user account.
     *
     * @param userId         The ID of the user account to update.
     * @param isActiveStatus The new active status value.
     */
    void updateIsActiveStatus(String userId, boolean isActiveStatus);

    /**
     * Updates the email address of the user account.
     *
     * @param userId The ID of the user account to update.
     * @param email  The new email address.
     * @return True if the email address was successfully updated, false otherwise.
     */
    boolean updateEmail(String userId, String email);

    /**
     * Updates the password of the user account.
     *
     * @param userId   The ID of the user account to update.
     * @param password The new password.
     * @return True if the password was successfully updated, false otherwise.
     */
    boolean updatePassword(String userId, String password);
}
