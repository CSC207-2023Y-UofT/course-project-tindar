package com.courseproject.tindar.usecases.editaccount;

public class EditAccountInteractor implements EditAccountInputBoundary {
    final EditAccountDsGateway editAccountDsGateway;
    
    public EditAccountInteractor(EditAccountDsGateway editAccountDsGateway) {
        this.editAccountDsGateway = editAccountDsGateway;
    }
    
    public EditAccountDsResponseModel getAccount(String userId) {
        return editAccountDsGateway.readAccount(userId);
    }

    public void updateIsActiveStatus(String userId, boolean isActiveStatus) {
        this.editAccountDsGateway.updateIsActiveStatus(userId, isActiveStatus);
    }

    public boolean updateEmail(String userId, String email) {
        return this.editAccountDsGateway.updateEmail(userId, email);
    }

    public boolean updatePassword(String userId, String password) {
        if (password.length() < 6) {
            return false;
        }
        return this.editAccountDsGateway.updatePassword(userId, password);
    }
}
