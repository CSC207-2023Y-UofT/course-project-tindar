package com.courseproject.tindar.controllers.editaccount;

import com.courseproject.tindar.usecases.editaccount.EditAccountInputBoundary;
import com.courseproject.tindar.usecases.editaccount.EditAccountDsResponseModel;

import java.util.Objects;

/**
 * This class is responsible for handling user input from UI layer and working to edit user profile
 */
public class EditAccountController {
    /**
     * The input boundary interface for handling user input and processing.
     */
    final EditAccountInputBoundary userInput;

    /**
     * Creates a new EditAccountController.
     * @param EditAccountUserInput the object used to execute changes based on user input.
     */
    public EditAccountController(EditAccountInputBoundary EditAccountUserInput) {
        this.userInput = EditAccountUserInput;
    }

    /**
     * Retrieves an account's details.
     *
     * @param userId the user id of the account
     * @return a EditAccountDsResponseModel holding the details of the account
     */
    public EditAccountDsResponseModel getAccount(String userId) {
        return userInput.getAccount(userId);
    }

    /**
     * Compares an inputted password to the password of an account.
     *
     * @param userId the user id of the account
     * @param password the password that was inputted
     * @return true if the passwords match; false otherwise
     */
    public boolean validatePassword(String userId, String password) {
        String vPassword = getAccount(userId).getPassword();
        return Objects.equals(vPassword, password);
    }

    /**
     * Sets the status of an account.
     *
     * @param userId the user id of the account
     * @param isActiveStatus the new active status
     */
    public void updateIsActiveStatus(String userId, boolean isActiveStatus) {
        this.userInput.updateIsActiveStatus(userId, isActiveStatus);
    }

    /**
     * Changes the email associated with an account.
     * Returns false if an email is already used by an account.
     *
     * @param userId the user id of the account
     * @param email the new email to be associated with the account
     * @return true if email was successfully updated; false otherwise.
     */
    public boolean updateEmail(String userId, String email) {
        if (email.isEmpty()) {
            return false;
        }
        else {
            return this.userInput.updateEmail(userId, email);
        }
    }

    /**
     * Changes the password associated with an account.
     *
     * @param userId the user id of the account
     * @param password the new password to be associated with the account
     */
    public boolean updatePassword(String userId, String password) {
        if (password.length() < 6) {
            return false;
        }
        else {
            return this.userInput.updatePassword(userId, password);
        }
    }
}
