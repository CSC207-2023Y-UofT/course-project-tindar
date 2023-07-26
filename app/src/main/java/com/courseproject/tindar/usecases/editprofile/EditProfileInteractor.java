package com.courseproject.tindar.usecases.editprofile;

import java.util.Date;

public class EditProfileInteractor implements EditProfileInputBoundary {
    final EditProfileDsGateway editProfileDsGateway;

    public EditProfileInteractor(EditProfileDsGateway editProfileDsGateway) {
        this.editProfileDsGateway = editProfileDsGateway;
    }

    public EditProfileDsResponseModel getProfile(String userId) {
        return editProfileDsGateway.readProfile(userId);
    }

    public void updateBirthdate(String userId, Date birthdate) {
        editProfileDsGateway.updateBirthdate(userId, birthdate);
    }

    public void updateGender(String userId, String gender) {
        editProfileDsGateway.updateGender(userId, gender);
    }

    public void updateLocation(String userId, String location) {
        editProfileDsGateway.updateLocation(userId, location);
    }

    public void updateProfilePictureLink(String userId, String profilePictureLink) {
        editProfileDsGateway.updateProfilePictureLink(userId, profilePictureLink);
    }

    public void updateAboutMe(String userId, String aboutMe) {
        editProfileDsGateway.updateAboutMe(userId, aboutMe);
    }
}
