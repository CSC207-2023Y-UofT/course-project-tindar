package com.courseproject.tindar.usecases.editprofile;

import java.util.Date;

public interface EditProfileDsGateway {
    EditProfileResponseModel readProfile(String userId);

    void updateBirthdate(String userId, Date birthdate);

    void updateGender(String userId, String gender);

    void updateLocation(String userId, String location);

    void updateProfilePictureLink(String userId, String profilePictureLink);

    void updateAboutMe(String userId, String aboutMe);
}
