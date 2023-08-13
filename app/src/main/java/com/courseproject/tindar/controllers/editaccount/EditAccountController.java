package com.courseproject.tindar.controllers.editaccount;

import com.courseproject.tindar.usecases.editaccount.EditAccountInputBoundary;
import com.courseproject.tindar.usecases.editaccount.EditAccountDsResponseModel;

import java.util.Objects;

public class EditAccountController {
    final EditAccountInputBoundary userInput;

    public EditAccountController(EditAccountInputBoundary EditAccountUserInput) {
        this.userInput = EditAccountUserInput;
    }

    /** Retrieves the status, email, and password of an account.
     *
     * @param userId the user id of the account
     * @return an object holding the details of the account
     */
    public EditAccountDsResponseModel getAccount(String userId) {
        return userInput.getAccount(userId);
    }

    /** Compares an inputted password to the password of an account.
     *
     * @param userId the user id of the account
     * @param password the password that was inputted
     * @return true if the passwords match
     */
    public boolean validatePassword(String userId, String password) {
        String vPassword = getAccount(userId).getPassword();
        return Objects.equals(vPassword, password);
    }

    /** Change the status of an account to another status.
     *
     * @param userId the user id of the account
     * @param isActiveStatus the status for the account to be changed to
     */
    public void updateIsActiveStatus(String userId, boolean isActiveStatus) {
        this.userInput.updateIsActiveStatus(userId, isActiveStatus);
    }

    /** Change the email associated with an account.
     * Returns false if an email is already used by an account.
     *
     * @param userId the user id of the account
     * @param email the new email to be associated with the account
     * @return true if email was successfully updated
     */
    public boolean updateEmail(String userId, String email) {
        return this.userInput.updateEmail(userId, email);
    }

    /** Change the password associated with an account.
     *
     * @param userId the user id of the account
     * @param password the new password to be associated with the account
     */
    public boolean updatePassword(String userId, String password) {
        return this.userInput.updatePassword(userId, password);
    }
}
