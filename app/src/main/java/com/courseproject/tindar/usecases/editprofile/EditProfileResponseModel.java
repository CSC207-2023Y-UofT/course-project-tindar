package com.courseproject.tindar.usecases.editprofile;

import java.util.Date;

import lombok.Getter;

public class EditProfileResponseModel {

    @Getter private final String displayName;
    @Getter private final Date birthdate;
    @Getter private final String gender;
    @Getter private final String location;
    @Getter private final String profilePictureLink;
    @Getter private final String aboutMe;

    public EditProfileResponseModel(String displayName, Date birthdate, String gender, String location,
                                    String profilePictureLink,
                                    String aboutMe) {
        this.displayName = displayName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.location = location;
        this.profilePictureLink = profilePictureLink;
        this.aboutMe = aboutMe;
    }
}
