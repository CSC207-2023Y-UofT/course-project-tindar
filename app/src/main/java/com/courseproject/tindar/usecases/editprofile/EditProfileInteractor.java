package com.courseproject.tindar.usecases.editprofile;

public class EditProfileInteractor implements EditProfileInputBoundary {
    final EditProfileDsGateway editProfileDsGateway;

    public EditProfileInteractor(EditProfileDsGateway editProfileDsGateway) {
        this.editProfileDsGateway = editProfileDsGateway;
    }

    public EditProfileResponseModel getProfile(String userId) {
        return editProfileDsGateway.readProfile(userId);
    }

    @Override
    public void updateProfile(String userId, EditProfileRequestModel newProfile) {
        editProfileDsGateway.updateProfile(userId, newProfile);
    }
}
