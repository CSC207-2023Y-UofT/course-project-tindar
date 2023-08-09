package com.courseproject.tindar.usecases.editprofile;

import java.util.Date;

public interface EditProfileInputBoundary {
    EditProfileResponseModel getProfile(String userId);
    void updateProfile(String userId, EditProfileRequestModel newProfile);
}
