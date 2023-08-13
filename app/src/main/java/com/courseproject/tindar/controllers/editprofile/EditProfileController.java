package com.courseproject.tindar.controllers.editprofile;

import com.courseproject.tindar.usecases.editprofile.EditProfileInputBoundary;
import com.courseproject.tindar.usecases.editprofile.EditProfileRequestModel;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileInputBoundary;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileResponseModel;

/**
 * controller for the Edit Profile UI
 */
public class EditProfileController {
    /**
     * interactor of the Edit Profile feature
     */
    final EditProfileInputBoundary editProfileUserInput;
    /**
     * interactor of the View Profile feature
     */
    final ViewProfileInputBoundary viewProfileUserInput;

    /**
     * constructs controller for the Edit Profile UI
     *
     * @param editProfileUserInput interactor of the Edit Profile feature
     * @param viewProfileUserInput interactor of the View Profile feature
     */
    public EditProfileController(EditProfileInputBoundary editProfileUserInput, ViewProfileInputBoundary viewProfileUserInput) {
        this.editProfileUserInput = editProfileUserInput;
        this.viewProfileUserInput = viewProfileUserInput;
    }

    /**
     * gets profile information of the user
     *
     * @param userId the user id of the account
     * @return profile information of the user
     */
    public ViewProfileResponseModel getProfile(String userId) {
        return viewProfileUserInput.getProfile(userId);
    }

    /**
     * updates profile information of the user
     *
     * @param userId the user id of the account
     * @param newProfile new profile information to be updated
     */
    public void updateProfile(String userId, EditProfileRequestModel newProfile) {
        editProfileUserInput.updateProfile(userId, newProfile);
    }
}
