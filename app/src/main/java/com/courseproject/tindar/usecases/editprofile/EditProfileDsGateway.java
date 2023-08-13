package com.courseproject.tindar.usecases.editprofile;

/**
 * interface for the data-saving gateway of Edit Profile feature
 */
public interface EditProfileDsGateway {
    /**
     * @param userId the user id of the account
     * @param newProfile new profile information of the user to be updated
     */
    void updateProfile(String userId, EditProfileRequestModel newProfile);
}
