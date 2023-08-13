package com.courseproject.tindar.controllers.viewprofile;

import static org.junit.Assert.assertEquals;

import com.courseproject.tindar.controllers.viewprofiles.ViewProfilesController;
import com.courseproject.tindar.usecases.likelist.LikeListInputBoundary;
import com.courseproject.tindar.usecases.userlist.UserListInputBoundary;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileInputBoundary;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileResponseModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class ViewProfilesControllerUnitTest {
    /**
     * This class tests the implementation and return values of ViewProfileController
     **/
    private static final String DISPLAY_NAME = "john";
    private static final String USER_ID = "99";
    private static final Date BIRTHDATE = new GregorianCalendar(1999, 3, 10).getTime();
    private static final String GENDER = "Male";
    private static final String LOCATION = "Toronto";
    private static final String PROFILE_PICTURE_LINK = "https://aaa";
    private static final String ABOUT_ME = "Hello!";
    private static final String USER_ID_1 = "user1";
    private static final String USER_ID_2 = "user2";
    private static final boolean DOES_LIKE = true;

    ViewProfileResponseModel mockViewProfileResponseModel =
            new ViewProfileResponseModel(DISPLAY_NAME, BIRTHDATE, GENDER, LOCATION, PROFILE_PICTURE_LINK, ABOUT_ME);

    /**
     * Mock implementation of ViewProfileInputBoundary for testing purposes
     **/
    private class MockViewProfileUserInput implements ViewProfileInputBoundary {
        @Override
        public ViewProfileResponseModel getProfile(String userId) {
            return mockViewProfileResponseModel;
        }
    }

    /**
     * Mock implementation of UserListInputBoundary for testing purposes
     **/
    private static class MockUserListUserInput implements UserListInputBoundary {
        @Override
        public ArrayList<String> getAllOtherUserIds(String userId) {
            assertEquals(USER_ID, userId);
            ArrayList<String> allOtherUserIds = new ArrayList<>();
            allOtherUserIds.add("some-user");
            allOtherUserIds.add("some-other-user");
            return allOtherUserIds;
        }
    }
    /**
     * Mock implementation of LikeListInputBoundary for testing purposes
     **/
    private static class MockLikeListUserInput implements LikeListInputBoundary {
        @Override
        public boolean checkLiked(String userId, String otherUserId) {
            // Mock checkLiked implementation
            assertEquals(USER_ID_1, userId);
            assertEquals(USER_ID_2, otherUserId);
            return DOES_LIKE;
        }

        @Override
        public void addLike(String userId, String otherUserId) {
            // Mock addLike implementation
            assertEquals(USER_ID_1, userId);
            assertEquals(USER_ID_2, otherUserId);
        }

        @Override
        public void removeLike(String userId, String otherUserId) {
            // Mock removeLike implementation
            assertEquals(USER_ID_1, userId);
            assertEquals(USER_ID_2, otherUserId);
        }
    }

    ViewProfileInputBoundary mockViewProfileUserInput = new MockViewProfileUserInput();
    UserListInputBoundary mockUserListUserInput = new MockUserListUserInput();
    LikeListInputBoundary mockLikeListUserInput = new MockLikeListUserInput();

    @Test
    public void testGetProfile() {
        ViewProfilesController testViewProfilesController = new ViewProfilesController(mockViewProfileUserInput,
                mockUserListUserInput, mockLikeListUserInput);
        ViewProfileResponseModel testProfile = testViewProfilesController.getProfile(USER_ID);
        assertEquals(DISPLAY_NAME, testProfile.getDisplayName());
        assertEquals(BIRTHDATE, testProfile.getBirthdate());
        assertEquals(GENDER, testProfile.getGender());
        assertEquals(LOCATION, testProfile.getLocation());
        assertEquals(PROFILE_PICTURE_LINK, testProfile.getProfilePictureLink());
        assertEquals(ABOUT_ME, testProfile.getAboutMe());
    }

    @Test
    public void testGetAllOtherUserIds() {
        ViewProfilesController testViewProfilesController = new ViewProfilesController(mockViewProfileUserInput,
                mockUserListUserInput, mockLikeListUserInput);
        ArrayList<String> testAllOtherUserIds = testViewProfilesController.getAllOtherUserIds(USER_ID);
        assertEquals(2, testAllOtherUserIds.size());
        assertEquals("some-user", testAllOtherUserIds.get(0));
        assertEquals("some-other-user", testAllOtherUserIds.get(1));
    }

    @Test
    public void testCheckLiked() {
        ViewProfilesController testViewProfilesController = new ViewProfilesController(mockViewProfileUserInput,
                mockUserListUserInput, mockLikeListUserInput);
        boolean doesLike = testViewProfilesController.checkLiked(USER_ID_1, USER_ID_2);
        assertEquals(DOES_LIKE, doesLike);
    }

    @Test
    public void testAddLike() {
        // Test that AddLike is called
        ViewProfilesController testViewProfilesController = new ViewProfilesController(mockViewProfileUserInput,
                mockUserListUserInput, mockLikeListUserInput);
        testViewProfilesController.addLike(USER_ID_1, USER_ID_2);
    }

    @Test
    public void testRemoveLike() {
        // Test thatRemoveLike is called
        ViewProfilesController testViewProfilesController = new ViewProfilesController(mockViewProfileUserInput,
                mockUserListUserInput, mockLikeListUserInput);
        testViewProfilesController.removeLike(USER_ID_1, USER_ID_2);
    }
}
