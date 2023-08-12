package com.courseproject.tindar.controllers.editprofile;

import static org.junit.Assert.assertEquals;

import com.courseproject.tindar.usecases.editprofile.EditProfileRequestModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileInputBoundary;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileInputBoundary;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileResponseModel;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class EditProfileControllerUnitTest {
    private static final String DISPLAY_NAME = "john";
    private static final String USER_ID = "99";
    private static final Date BIRTHDATE = new GregorianCalendar(1999, 3, 10).getTime();
    private static final String GENDER = "Male";
    private static final String LOCATION = "Toronto";
    private static final String PROFILE_PICTURE_LINK = "https://aaa";
    private static final String ABOUT_ME = "Hello!";

    ViewProfileResponseModel mockViewProfileResponseModel =
        new ViewProfileResponseModel(DISPLAY_NAME, BIRTHDATE, GENDER, LOCATION, PROFILE_PICTURE_LINK, ABOUT_ME);

    private static class MockEditProfileUserInput implements EditProfileInputBoundary {
        @Override
        public void updateProfile(String userId, EditProfileRequestModel newProfile) {
            assertEquals(USER_ID, userId);
            assertEquals(BIRTHDATE, newProfile.getBirthdate());
            assertEquals(GENDER, newProfile.getGender());
            assertEquals(LOCATION, newProfile.getLocation());
            assertEquals(PROFILE_PICTURE_LINK, newProfile.getProfilePictureLink());
            assertEquals(ABOUT_ME, newProfile.getAboutMe());
        }
    }

    private class MockViewProfileUserInput implements ViewProfileInputBoundary {
        @Override
        public ViewProfileResponseModel getProfile(String userId) {
            return mockViewProfileResponseModel;
        }
    }

    ViewProfileInputBoundary mockViewProfileUserInput = new MockViewProfileUserInput();
    EditProfileInputBoundary mockEditProfileUserInput = new MockEditProfileUserInput();

    @Test
    public void testGetProfile() {
        EditProfileController testEditProfileController = new EditProfileController(mockEditProfileUserInput, mockViewProfileUserInput);
        ViewProfileResponseModel testProfile = testEditProfileController.getProfile(USER_ID);
        assertEquals(DISPLAY_NAME, testProfile.getDisplayName());
        assertEquals(BIRTHDATE, testProfile.getBirthdate());
        assertEquals(GENDER, testProfile.getGender());
        assertEquals(LOCATION, testProfile.getLocation());
        assertEquals(PROFILE_PICTURE_LINK, testProfile.getProfilePictureLink());
        assertEquals(ABOUT_ME, testProfile.getAboutMe());
    }

    @Test
    public void testUpdateProfile() {
        EditProfileController testEditProfileController = new EditProfileController(mockEditProfileUserInput, mockViewProfileUserInput);
        EditProfileRequestModel newProfile = new EditProfileRequestModel(BIRTHDATE, GENDER, LOCATION,
                PROFILE_PICTURE_LINK, ABOUT_ME);
        testEditProfileController.updateProfile(USER_ID, newProfile);
    }
}
