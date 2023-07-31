package com.courseproject.tindar.controllers.editaccount;

import com.courseproject.tindar.usecases.editaccount.EditAccountInputBoundary;
import com.courseproject.tindar.usecases.editaccount.EditAccountDsResponseModel;

import java.util.Objects;

public class EditAccountController {
    final EditAccountInputBoundary userInput;

    public EditAccountController(EditAccountInputBoundary EditAccountUserInput) {
        this.userInput = EditAccountUserInput;
    }

    public EditAccountDsResponseModel getAccount(String userId) {
        return userInput.getAccount(userId);
    }

    public boolean validateChanges(String userId, String password) {
        String vPassword = this.userInput.getAccount(userId).getPassword();
        return Objects.equals(vPassword, password);
    }

    public void updateIsActiveStatus(String userId, boolean isActiveStatus) {
        this.userInput.updateIsActiveStatus(userId, isActiveStatus);
    }

    public void updateEmail(String userId, String email) {
        this.userInput.updateEmail(userId, email);
    }

    public void updatePassword(String userId, String password) {
        this.userInput.updatePassword(userId, password);
    }
}
