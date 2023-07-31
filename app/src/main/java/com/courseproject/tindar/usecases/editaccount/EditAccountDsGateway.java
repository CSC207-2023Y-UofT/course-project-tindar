package com.courseproject.tindar.usecases.editaccount;

public interface EditAccountDsGateway {
    EditAccountDsResponseModel readAccount(String userId);

    void updateIsActiveStatus(String userId, boolean isActiveStatus);

    void updateEmail(String userId, String email);

    void updatePassword(String userId, String password);
}
