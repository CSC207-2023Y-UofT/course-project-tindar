package com.courseproject.tindar.controllers.viewprofiles;

import static org.junit.Assert.assertEquals;

import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesDsResponseModel;
import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesInputBoundary;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

public class ViewProfilesControllerUnitTest {

    private static final String DISPLAY_NAME = "john";
    private static final String USER_ID = "99";
    private static final Date BIRTHDATE = new GregorianCalendar(1999, 3, 10).getTime();
    private static final String GENDER = "Male";
    private static final String LOCATION = "Toronto";
    private static final String PROFILE_PICTURE_LINK = "https://aaa";
    private static final String ABOUT_ME = "Hello!";

    ViewProfilesDsResponseModel mockViewProfilesResponseModel =
            new ViewProfilesDsResponseModel(DISPLAY_NAME, BIRTHDATE, GENDER, LOCATION, PROFILE_PICTURE_LINK, ABOUT_ME);

    private class MockViewProfilesUserInput implements ViewProfilesInputBoundary {
        @Override
        public ViewProfilesDsResponseModel readNextProfile(String userId) {
            return mockViewProfilesResponseModel;
        }
    }

    @Test
    public void testReadNextProfile(){
        MockViewProfilesUserInput mockViewProfilesUserInput = new MockViewProfilesUserInput();
        assertEquals(mockViewProfilesResponseModel, mockViewProfilesUserInput.readNextProfile(USER_ID));
    }
}
