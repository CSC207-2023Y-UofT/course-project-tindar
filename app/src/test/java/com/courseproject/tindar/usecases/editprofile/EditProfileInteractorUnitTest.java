package com.courseproject.tindar.usecases.editprofile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class EditProfileInteractorUnitTest {

    private static final String DISPLAY_NAME = "john";
    private static final String USER_ID = "99";
    private static final Date BIRTHDATE = new GregorianCalendar(1999, 3, 10).getTime();
    private static final String GENDER = "Male";
    private static final String LOCATION = "Toronto";
    private static final String PROFILE_PICTURE_LINK = "https://aaa";
    private static final String ABOUT_ME = "Hello!";

    final EditProfileResponseModel mockEditProfileResponseModel =
        new EditProfileResponseModel(DISPLAY_NAME, BIRTHDATE, GENDER, LOCATION, PROFILE_PICTURE_LINK, ABOUT_ME);

    private class MockEditProfileDsGateway implements EditProfileDsGateway {
        public EditProfileResponseModel readProfile(String userId) {
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

    final EditProfileDsGateway mockEditProfileDsGateway = new MockEditProfileDsGateway();

    @Test
    public void testGetProfile() {
        EditProfileInteractor testEditProfileInteractor = new EditProfileInteractor(mockEditProfileDsGateway);
        EditProfileResponseModel testEditProfileResponseModel = testEditProfileInteractor.getProfile(USER_ID);
        assertEquals(DISPLAY_NAME, testEditProfileResponseModel.getDisplayName());
        assertEquals(BIRTHDATE, testEditProfileResponseModel.getBirthdate());
        assertEquals(GENDER, testEditProfileResponseModel.getGender());
        assertEquals(LOCATION, testEditProfileResponseModel.getLocation());
        assertEquals(PROFILE_PICTURE_LINK, testEditProfileResponseModel.getProfilePictureLink());
        assertEquals(ABOUT_ME, testEditProfileResponseModel.getAboutMe());
    }

    @Test
    public void testUpdateProfile() {
        EditProfileInteractor testEditProfileInteractor = new EditProfileInteractor(mockEditProfileDsGateway);
        EditProfileRequestModel newProfile = new EditProfileRequestModel(BIRTHDATE, GENDER, LOCATION,
                PROFILE_PICTURE_LINK, ABOUT_ME);
        testEditProfileInteractor.updateProfile(USER_ID, newProfile);
    }
}
