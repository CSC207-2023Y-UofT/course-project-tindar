package com.courseproject.tindar.usecases.editaccount;

public interface EditAccountInputBoundary {
    EditAccountDsResponseModel getAccount(String userId);

    void updateIsActiveStatus(String userId, boolean isActiveStatus);

    boolean updateEmail(String userId, String email);

    boolean updatePassword(String userId, String password);
}
