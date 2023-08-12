package com.courseproject.tindar.usecases.editprofile;

/**
 * interactor of the Edit Profile feature
 */
public interface EditProfileInputBoundary {
    /**
     * @param userId the user id of the account
     * @return profile information of the user
     */
    EditProfileResponseModel getProfile(String userId);

    /**
     * @param userId the user id of the account
     * @param newProfile new profile information of the user to be updated
     */
    void updateProfile(String userId, EditProfileRequestModel newProfile);
}
