package com.courseproject.tindar.controllers.editprofile;

import static org.junit.Assert.assertEquals;

import com.courseproject.tindar.usecases.editprofile.EditProfileInputBoundary;
import com.courseproject.tindar.usecases.editprofile.EditProfileResponseModel;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class EditProfileControllerUnitTest {
    private static final String USER_ID = "99";
    private static final Date BIRTHDATE = new GregorianCalendar(1999, 3, 10).getTime();
    private static final String GENDER = "Male";
    private static final String LOCATION = "Toronto";
    private static final String PROFILE_PICTURE_LINK = "https://aaa";
    private static final String ABOUT_ME = "Hello!";

    EditProfileResponseModel mockEditProfileResponseModel =
        new EditProfileResponseModel(BIRTHDATE, GENDER, LOCATION, PROFILE_PICTURE_LINK, ABOUT_ME);

    private class MockEditProfileUserInput implements EditProfileInputBoundary {
        public EditProfileResponseModel getProfile(String userId) {
            return mockEditProfileResponseModel;
        }

        public void updateBirthdate(String userId, Date birthdate) {
            assertEquals(userId, USER_ID);
            assertEquals(birthdate, BIRTHDATE);
        }

        public void updateGender(String userId, String gender) {
            assertEquals(userId, USER_ID);
            assertEquals(gender, GENDER);
        }

        public void updateLocation(String userId, String location) {
            assertEquals(USER_ID, userId);
            assertEquals(LOCATION, location);
        }

        public void updateProfilePictureLink(String userId, String profilePictureLink) {
            assertEquals(USER_ID, userId);
            assertEquals(PROFILE_PICTURE_LINK, profilePictureLink);
        }

        public void updateAboutMe(String userId, String aboutMe) {
            assertEquals(USER_ID, userId);
            assertEquals(ABOUT_ME, aboutMe);
        }
    }

    EditProfileInputBoundary mockEditProfileUserInput = new MockEditProfileUserInput();

    @Test
    public void getProfile() {
        EditProfileController testEditProfileController = new EditProfileController(mockEditProfileUserInput);
        EditProfileResponseModel testEditProfileResponseModel = testEditProfileController.getProfile(USER_ID);
        assertEquals(BIRTHDATE, testEditProfileResponseModel.getBirthdate());
        assertEquals(GENDER, testEditProfileResponseModel.getGender());
        assertEquals(LOCATION, testEditProfileResponseModel.getLocation());
        assertEquals(PROFILE_PICTURE_LINK, testEditProfileResponseModel.getProfilePictureLink());
        assertEquals(ABOUT_ME, testEditProfileResponseModel.getAboutMe());
    }

    @Test
    public void updateBirthdate() {
        EditProfileController testEditProfileController = new EditProfileController(mockEditProfileUserInput);
        testEditProfileController.updateBirthdate(USER_ID, BIRTHDATE);
    }

    @Test
    public void updateGender() {
        EditProfileController testEditProfileController = new EditProfileController(mockEditProfileUserInput);
        testEditProfileController.updateGender(USER_ID, GENDER);
    }

    @Test
    public void updateLocation() {
        EditProfileController testEditProfileController = new EditProfileController(mockEditProfileUserInput);
        testEditProfileController.updateLocation(USER_ID, LOCATION);
    }

    @Test
    public void updateProfilePictureLink() {
        EditProfileController testEditProfileController = new EditProfileController(mockEditProfileUserInput);
        testEditProfileController.updateProfilePictureLink(USER_ID, PROFILE_PICTURE_LINK);
    }

    @Test
    public void updateAboutMe() {
        EditProfileController testEditProfileController = new EditProfileController(mockEditProfileUserInput);
        testEditProfileController.updateAboutMe(USER_ID, ABOUT_ME);
    }
}
