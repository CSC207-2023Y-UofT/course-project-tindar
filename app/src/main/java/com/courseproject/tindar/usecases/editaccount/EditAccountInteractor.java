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

    public void updateEmail(String userId, String email) {
        this.editAccountDsGateway.updateEmail(userId, email);
    }

    public void updatePassword(String userId, String password) {
        this.editAccountDsGateway.updatePassword(userId, password);
    }
}
