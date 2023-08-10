package com.courseproject.tindar.usecases.editprofile;

import java.util.Date;

public interface EditProfileDsGateway {
    EditProfileResponseModel readProfile(String userId);
    void updateProfile(String userId, EditProfileRequestModel newProfile);
}
