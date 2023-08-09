package com.courseproject.tindar.controllers.editprofile;

import static org.junit.Assert.assertEquals;

import com.courseproject.tindar.usecases.editprofile.EditProfileRequestModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileResponseModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileInputBoundary;

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

    EditProfileResponseModel mockEditProfileResponseModel =
        new EditProfileResponseModel(DISPLAY_NAME, BIRTHDATE, GENDER, LOCATION, PROFILE_PICTURE_LINK, ABOUT_ME);

    private class MockEditProfileUserInput implements EditProfileInputBoundary {
        public EditProfileResponseModel getProfile(String userId) {
            return mockEditProfileResponseModel;
        }

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

    EditProfileInputBoundary mockEditProfileUserInput = new MockEditProfileUserInput();

    @Test
    public void getProfile() {
        EditProfileController testEditProfileController = new EditProfileController(mockEditProfileUserInput);
        EditProfileResponseModel testEditProfileResponseModel = testEditProfileController.getProfile(USER_ID);
        assertEquals(DISPLAY_NAME, testEditProfileResponseModel.getDisplayName());
        assertEquals(BIRTHDATE, testEditProfileResponseModel.getBirthdate());
        assertEquals(GENDER, testEditProfileResponseModel.getGender());
        assertEquals(LOCATION, testEditProfileResponseModel.getLocation());
        assertEquals(PROFILE_PICTURE_LINK, testEditProfileResponseModel.getProfilePictureLink());
        assertEquals(ABOUT_ME, testEditProfileResponseModel.getAboutMe());
    }

    @Test
    public void testUpdateProfile() {
        EditProfileController testEditProfileController = new EditProfileController(mockEditProfileUserInput);
        EditProfileRequestModel newProfile = new EditProfileRequestModel(BIRTHDATE, GENDER, LOCATION,
                PROFILE_PICTURE_LINK, ABOUT_ME);
        testEditProfileController.updateProfile(USER_ID, newProfile);
    }
}
