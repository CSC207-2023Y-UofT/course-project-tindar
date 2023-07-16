package com.courseproject.tindar.controllers.editprofile;

import com.courseproject.tindar.usecases.editprofile.EditProfileInputBoundary;
import com.courseproject.tindar.usecases.editprofile.EditProfileResponseModel;

import java.util.Date;

public class EditProfileController {
    final EditProfileInputBoundary userInput;

    public EditProfileController(EditProfileInputBoundary editProfileUserInput) {
        this.userInput = editProfileUserInput;
    }

    public EditProfileResponseModel getProfile(String userId) {
        return userInput.getProfile(userId);
    }

    public void updateBirthdate(String userId, Date birthdate) {
        userInput.updateBirthdate(userId, birthdate);
    }

    public void updateGender(String userId, String gender) {
        userInput.updateGender(userId, gender);
    }

    public void updateLocation(String userId, String location) {
        userInput.updateLocation(userId, location);
    }

    public void updateProfilePictureLink(String userId, String profilePictureLink) {
        userInput.updateProfilePictureLink(userId, profilePictureLink);
    }

    public void updateAboutMe(String userId, String aboutMe) {
        userInput.updateAboutMe(userId, aboutMe);
    }
}
