package com.courseproject.tindar.usecases.editaccount;

/**
 * This class is the interactor of the edit account feature, where it
 * serves as the interactor for the edit account feature.
 *  It implements the EditAccountInputBoundary interface to handle interactions and
 *  operations related to account editing.
 *
 */
public class EditAccountInteractor implements EditAccountInputBoundary {
    /**
     * The gateway responsible for data source interactions related to account editing.
     */
    final EditAccountDsGateway editAccountDsGateway;

    /**
     * Constructs a new EditAccountInteractor object with the provided EditAccountDsGateway.
     *
     * @param editAccountDsGateway The gateway for data source interactions.
     */
    public EditAccountInteractor(EditAccountDsGateway editAccountDsGateway) {
        this.editAccountDsGateway = editAccountDsGateway;
    }

    /**
     * Retrieves the account information associated with the specified user ID.
     *
     * @param userId The ID of the user account to retrieve.
     * @return An EditAccountDsResponseModel containing the account information.
     */
    public EditAccountDsResponseModel getAccount(String userId) {
        return editAccountDsGateway.readAccount(userId);
    }

    /**
     * Updates the active status of the user account.
     *
     * @param userId         The ID of the user account to update.
     * @param isActiveStatus The new active status value.
     */
    public void updateIsActiveStatus(String userId, boolean isActiveStatus) {
        this.editAccountDsGateway.updateIsActiveStatus(userId, isActiveStatus);
    }

    /**
     * Updates the email address of the user account.
     *
     * @param userId The ID of the user account to update.
     * @param email  The new email address.
     * @return True if the email address was successfully updated, false otherwise.
     */
    public boolean updateEmail(String userId, String email) {
        return this.editAccountDsGateway.updateEmail(userId, email);
    }

    /**
     * Updates the password of the user account.
     *
     * @param userId   The ID of the user account to update.
     * @param password The new password.
     * @return True if the password was successfully updated, false otherwise.
     */
    public boolean updatePassword(String userId, String password) {
        return this.editAccountDsGateway.updatePassword(userId, password);
    }
}
