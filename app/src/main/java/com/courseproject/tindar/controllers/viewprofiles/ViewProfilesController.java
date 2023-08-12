package com.courseproject.tindar.controllers.viewprofiles;

import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesDsResponseModel;
import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesInputBoundary;

/**
 * The ViewProfilesController class is responsible for managing the viewing of user profiles
 *  and interactions related to profile data.
 *
 */

public class ViewProfilesController {
    final ViewProfilesInputBoundary userInput;

    public ViewProfilesController(ViewProfilesInputBoundary userInput) {
        this.userInput = userInput;
    }

    /**
     * Reads the next user profile information based on the provided user ID.
     *
     * @param userId The user ID for which to retrieve the next profile information.
     * @return A ViewProfilesDsResponseModel containing the next user profile information.
     */

    public ViewProfilesDsResponseModel readNextProfile(String userId){
        return userInput.readNextProfile(userId);
    }

}
