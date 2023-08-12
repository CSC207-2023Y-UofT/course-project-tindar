package com.courseproject.tindar.controllers.editprofile;

import com.courseproject.tindar.usecases.editprofile.EditProfileInputBoundary;
import com.courseproject.tindar.usecases.editprofile.EditProfileResponseModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileRequestModel;

public class EditProfileController {
    final EditProfileInputBoundary userInput;

    public EditProfileController(EditProfileInputBoundary editProfileUserInput) {
        this.userInput = editProfileUserInput;
    }

    public EditProfileResponseModel getProfile(String userId) {
        return userInput.getProfile(userId);
    }

    public void updateProfile(String userId, EditProfileRequestModel newProfile) {
        userInput.updateProfile(userId, newProfile);
    }
}
