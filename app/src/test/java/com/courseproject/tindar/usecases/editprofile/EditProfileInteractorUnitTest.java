package com.courseproject.tindar.usecases.editprofile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class EditProfileInteractorUnitTest {

    private static final String USER_ID = "99";
    private static final Date BIRTHDATE = new GregorianCalendar(1999, 3, 10).getTime();
    private static final String GENDER = "Male";
    private static final String LOCATION = "Toronto";
    private static final String PROFILE_PICTURE_LINK = "https://aaa";
    private static final String ABOUT_ME = "Hello!";

    EditProfileResponseModel mockEditProfileResponseModel =
        new EditProfileResponseModel(BIRTHDATE, GENDER, LOCATION, PROFILE_PICTURE_LINK, ABOUT_ME);

    private class MockEditProfileDsGateway implements EditProfileDsGateway {
        public EditProfileResponseModel readProfile(String userId) {
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

    EditProfileDsGateway mockEditProfileDsGateway = new MockEditProfileDsGateway();

    @Test
    public void getProfile() {
        EditProfileInteractor testEditProfileInteractor = new EditProfileInteractor(mockEditProfileDsGateway);
        EditProfileResponseModel testEditProfileResponseModel = testEditProfileInteractor.getProfile(USER_ID);
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
