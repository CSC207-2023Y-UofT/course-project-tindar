package com.courseproject.tindar.usecases.editprofile;

import java.util.Date;

public class EditProfileDsResponseModel {

    private final String displayName;
    private final Date birthdate;
    private final String gender;
    private final String location;
    private final String profilePictureLink;
    private final String aboutMe;

    public EditProfileDsResponseModel(String displayName, Date birthdate, String gender, String location,
                                      String profilePictureLink,
                                      String aboutMe) {
        this.displayName = displayName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.location = location;
        this.profilePictureLink = profilePictureLink;
        this.aboutMe = aboutMe;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return this.gender;
    }

    public String getLocation() {
        return this.location;
    }

    public String getProfilePictureLink() {
        return this.profilePictureLink;
    }

    public String getAboutMe() {
        return this.aboutMe;
    }
}
