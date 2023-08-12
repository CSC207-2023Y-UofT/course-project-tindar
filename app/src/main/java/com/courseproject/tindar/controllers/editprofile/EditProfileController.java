package com.courseproject.tindar.controllers.editprofile;

import com.courseproject.tindar.usecases.editprofile.EditProfileInputBoundary;
import com.courseproject.tindar.usecases.editprofile.EditProfileResponseModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileRequestModel;

/**
 * controller for the Edit Profile UI
 */
public class EditProfileController {
    /**
     * interactor of the Edit Profile feature
     */
    final EditProfileInputBoundary userInput;

    /**
     * constructs controller for the Edit Profile UI
     *
     * @param editProfileUserInput interactor of the Edit Profile feature
     */
    public EditProfileController(EditProfileInputBoundary editProfileUserInput) {
        this.userInput = editProfileUserInput;
    }

    /**
     * gets profile information of the user
     *
     * @param userId the user id of the account
     * @return profile information of the user
     */
    public EditProfileResponseModel getProfile(String userId) {
        return userInput.getProfile(userId);
    }

    /**
     * updates profile information of the user
     *
     * @param userId the user id of the account
     * @param newProfile new profile information to be updated
     */
    public void updateProfile(String userId, EditProfileRequestModel newProfile) {
        userInput.updateProfile(userId, newProfile);
    }
}
