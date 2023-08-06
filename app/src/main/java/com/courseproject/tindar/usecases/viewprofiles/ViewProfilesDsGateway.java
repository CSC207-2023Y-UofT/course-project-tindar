package com.courseproject.tindar.usecases.viewprofiles;

import com.courseproject.tindar.usecases.editprofile.EditProfileDsResponseModel;

public interface ViewProfilesDsGateway {

    ViewProfilesDsResponseModel readProfile(String userId);

}
