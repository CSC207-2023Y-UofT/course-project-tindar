package com.courseproject.tindar.usecases.viewprofile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class ViewProfileInteractorUnitTest {

    private static final String DISPLAY_NAME = "john";
    private static final String USER_ID = "99";
    private static final Date BIRTHDATE = new GregorianCalendar(1999, 3, 10).getTime();
    private static final String GENDER = "Male";
    private static final String LOCATION = "Toronto";
    private static final String PROFILE_PICTURE_LINK = "https://aaa";
    private static final String ABOUT_ME = "Hello!";

    ViewProfileResponseModel mockViewProfileResponseModel =
            new ViewProfileResponseModel(DISPLAY_NAME, BIRTHDATE, GENDER, LOCATION, PROFILE_PICTURE_LINK, ABOUT_ME);

    private class MockViewProfileDsGateway implements ViewProfileDsGateway {
        @Override
        public ViewProfileResponseModel readProfile(String userId) {
            return mockViewProfileResponseModel;
        }
    }

    ViewProfileDsGateway mockViewProfileDsGateway = new ViewProfileInteractorUnitTest.MockViewProfileDsGateway();

    @Test
    public void testGetProfile() {
        ViewProfileInteractor testViewProfileInteractor = new ViewProfileInteractor(mockViewProfileDsGateway);
        ViewProfileResponseModel testViewProfileResponseModel = testViewProfileInteractor.getProfile(USER_ID);
        assertEquals(DISPLAY_NAME, testViewProfileResponseModel.getDisplayName());
        assertEquals(BIRTHDATE, testViewProfileResponseModel.getBirthdate());
        assertEquals(GENDER, testViewProfileResponseModel.getGender());
        assertEquals(LOCATION, testViewProfileResponseModel.getLocation());
        assertEquals(PROFILE_PICTURE_LINK, testViewProfileResponseModel.getProfilePictureLink());
        assertEquals(ABOUT_ME, testViewProfileResponseModel.getAboutMe());
    }
}
