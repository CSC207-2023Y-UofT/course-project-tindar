package com.courseproject.tindar.controllers.viewprofiles;

import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesDsResponseModel;
import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesInputBoundary;

public class ViewProfilesController {
    final ViewProfilesInputBoundary userInput;

    public ViewProfilesController(ViewProfilesInputBoundary userInput) {
        this.userInput = userInput;
    }

    public ViewProfilesDsResponseModel readNextProfile(String userId){
        return userInput.readNextProfile(userId);
    }

}
