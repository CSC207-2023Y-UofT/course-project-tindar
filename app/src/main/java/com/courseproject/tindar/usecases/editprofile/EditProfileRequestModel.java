package com.courseproject.tindar.usecases.editprofile;

import java.util.Date;

import lombok.Getter;

/**
 * data model for the profile on the update request
 */
public class EditProfileRequestModel {

    /**
     * display name of the user
     */
    @Getter private final String displayName;
    /**
     * birthdate of the user
     */
    @Getter private final Date birthdate;
    /**
     * gender of the user
     */
    @Getter private final String gender;
    /**
     * location where the user lives in
     */
    @Getter private final String location;
    /**
     * link to the profile picture of the user
     */
    @Getter private final String profilePictureLink;
    /**
     * statement the user writes to introduce him/herself to other users
     */
    @Getter private final String aboutMe;

    /**
     * Constructs Edit Profile Model for fetching and updating profile
     *
     * @param displayName display name of the user
     * @param birthdate birthdate of the user
     * @param gender gender of the user
     * @param location location where the user lives in
     * @param profilePictureLink link to the profile picture of the user
     * @param aboutMe statement the user writes to introduce him/herself to other users
     */

    public EditProfileRequestModel(String displayName, Date birthdate, String gender, String location,
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
