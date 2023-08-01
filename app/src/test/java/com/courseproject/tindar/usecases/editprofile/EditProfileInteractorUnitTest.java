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

    EditProfileDsResponseModel mockEditProfileResponseModel =
        new EditProfileDsResponseModel(DISPLAY_NAME, BIRTHDATE, GENDER, LOCATION, PROFILE_PICTURE_LINK, ABOUT_ME);

    private class MockEditProfileDsGateway implements EditProfileDsGateway {
        public EditProfileDsResponseModel readProfile(String userId) {
            return mockEditProfileResponseModel;
        }

        @Override
        public void updateBirthdate(String userId, Date birthdate) {
            assertEquals(USER_ID, userId);
            assertEquals(BIRTHDATE, birthdate);
        }

        @Override
        public void updateGender(String userId, String gender) {
            assertEquals(USER_ID, userId);
            assertEquals(GENDER, gender);
        }

        @Override
        public void updateLocation(String userId, String location) {
            assertEquals(USER_ID, userId);
            assertEquals(LOCATION, location);
        }

        @Override
        public void updateProfilePictureLink(String userId, String profilePictureLink) {
            assertEquals(USER_ID, userId);
            assertEquals(PROFILE_PICTURE_LINK, profilePictureLink);
        }

        @Override
        public void updateAboutMe(String userId, String aboutMe) {
            assertEquals(USER_ID, userId);
            assertEquals(ABOUT_ME, aboutMe);
        }
    }

    EditProfileDsGateway mockEditProfileDsGateway = new MockEditProfileDsGateway();

    @Test
    public void getProfile() {
        EditProfileInteractor testEditProfileInteractor = new EditProfileInteractor(mockEditProfileDsGateway);
        EditProfileDsResponseModel testEditProfileResponseModel = testEditProfileInteractor.getProfile(USER_ID);
        assertEquals(DISPLAY_NAME, testEditProfileResponseModel.getDisplayName());
        assertEquals(BIRTHDATE, testEditProfileResponseModel.getBirthdate());
        assertEquals(GENDER, testEditProfileResponseModel.getGender());
        assertEquals(LOCATION, testEditProfileResponseModel.getLocation());
        assertEquals(PROFILE_PICTURE_LINK, testEditProfileResponseModel.getProfilePictureLink());
        assertEquals(ABOUT_ME, testEditProfileResponseModel.getAboutMe());
    }

    @Test
    public void updateBirthdate() {
        EditProfileInteractor testEditProfileInteractor = new EditProfileInteractor(mockEditProfileDsGateway);
        testEditProfileInteractor.updateBirthdate(USER_ID, BIRTHDATE);
    }

    @Test
    public void updateGender() {
        EditProfileInteractor testEditProfileInteractor = new EditProfileInteractor(mockEditProfileDsGateway);
        testEditProfileInteractor.updateGender(USER_ID, GENDER);
    }

    @Test
    public void updateLocation() {
        EditProfileInteractor testEditProfileInteractor = new EditProfileInteractor(mockEditProfileDsGateway);
        testEditProfileInteractor.updateLocation(USER_ID, LOCATION);
    }

    @Test
    public void updateProfilePictureLink() {
        EditProfileInteractor testEditProfileInteractor = new EditProfileInteractor(mockEditProfileDsGateway);
        testEditProfileInteractor.updateProfilePictureLink(USER_ID, PROFILE_PICTURE_LINK);
    }

    @Test
    public void updateAboutMe() {
        EditProfileInteractor testEditProfileInteractor = new EditProfileInteractor(mockEditProfileDsGateway);
        testEditProfileInteractor.updateAboutMe(USER_ID, ABOUT_ME);
    }
}
