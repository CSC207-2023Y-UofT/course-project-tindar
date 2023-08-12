package com.courseproject.tindar.usecases.editprofile;

public interface EditProfileDsGateway {
    EditProfileResponseModel readProfile(String userId);
    void updateProfile(String userId, EditProfileRequestModel newProfile);
}
