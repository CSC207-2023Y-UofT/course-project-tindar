package com.courseproject.tindar.usecases.editprofile;

/**
 * interactor of the Edit Profile feature
 */
public class EditProfileInteractor implements EditProfileInputBoundary {
    /**
     * data-saving gateway of Edit Profile feature
     */
    final EditProfileDsGateway editProfileDsGateway;

    /**
     * constructs the interactor of the Edit Profile feature
     *
     * @param editProfileDsGateway data-saving gateway of Edit Profile feature
     */
    public EditProfileInteractor(EditProfileDsGateway editProfileDsGateway) {
        this.editProfileDsGateway = editProfileDsGateway;
    }

    /**
     * gets profile information of the user
     *
     * @param userId the user id of the account
     * @return profile information of the user
     */
    public EditProfileResponseModel getProfile(String userId) {
        return editProfileDsGateway.readProfile(userId);
    }

    /**
     * updates profile information of the user
     *
     * @param userId the user id of the account
     * @param newProfile new profile information of the user to be updated
     */
    @Override
    public void updateProfile(String userId, EditProfileRequestModel newProfile) {
        editProfileDsGateway.updateProfile(userId, newProfile);
    }
}
