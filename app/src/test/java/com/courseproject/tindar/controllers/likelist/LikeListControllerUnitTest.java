package com.courseproject.tindar.controllers.likelist;

import com.courseproject.tindar.usecases.likelist.LikeListInputBoundary;
import com.courseproject.tindar.usecases.likelist.LikeListResponseModel;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LikeListControllerUnitTest {
    /**
     * This class tests the implementation and return values of LikeListController
     **/
    private static final String USER_ID_1 = "user1";
    private static final String USER_ID_2 = "user2";

    public static class MockLikeListUserInput implements LikeListInputBoundary {
        /**
         * Mock implementation of LikeListInputBoundary for testing purposes
         **/

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

        @Override
        public LikeListResponseModel getDisplayNamesForMatches(String userId) {
            // Mock list of display names from match list
            assertEquals(USER_ID_1, userId);
            return new LikeListResponseModel(new String[]{}, new String[]{});
        }
    }

    @Test
    public void testAddLike() {
        // Test that AddLike is called
        LikeListInputBoundary testLikeListUserInput = new MockLikeListUserInput();
        LikeListController testLikeListController = new LikeListController(testLikeListUserInput);
        testLikeListController.addLike(USER_ID_1, USER_ID_2);
    }

    @Test
    public void testRemoveLike() {
        // Test thatRemoveLike is called
        LikeListInputBoundary testLikeListUserInput = new MockLikeListUserInput();
        LikeListController testLikeListController = new LikeListController(testLikeListUserInput);
        testLikeListController.removeLike(USER_ID_1, USER_ID_2);
    }

    @Test
    public void testGetDisplayNamesForMatches() {
        // Test that GetDisplayNamesForMatches is called
        LikeListInputBoundary testLikeListUserInput = new MockLikeListUserInput();
        LikeListController testLikeListController = new LikeListController(testLikeListUserInput);
        testLikeListController.getDisplayNamesForMatches(USER_ID_1);
    }
}
