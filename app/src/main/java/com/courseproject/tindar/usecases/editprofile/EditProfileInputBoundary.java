package com.courseproject.tindar.usecases.editprofile;

public interface EditProfileInputBoundary {
    EditProfileResponseModel getProfile(String userId);
    void updateProfile(String userId, EditProfileRequestModel newProfile);
}
