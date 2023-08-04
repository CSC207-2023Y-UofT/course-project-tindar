package com.courseproject.tindar.controllers.likelist;

import com.courseproject.tindar.usecases.likelist.LikeListInputBoundary;

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
            assertEquals(USER_ID_1, userId);
            assertEquals(USER_ID_2, otherUserId);
        }

        @Override
        public void removeLike(String userId, String otherUserId) {
            assertEquals(USER_ID_1, userId);
            assertEquals(USER_ID_2, otherUserId);
        }

        @Override
        public String[] getDisplayNamesForMatches(String userId) {
            return new String[]{};
        }
    }

    @Test
    public void testAddLike() {
        LikeListInputBoundary testLikeListUserInput = new MockLikeListUserInput();
        LikeListController testLikeListController = new LikeListController(testLikeListUserInput);
        testLikeListController.addLike(USER_ID_1, USER_ID_2);
    }

    @Test
    public void testRemoveLike() {
        LikeListInputBoundary testLikeListUserInput = new MockLikeListUserInput();
        LikeListController testLikeListController = new LikeListController(testLikeListUserInput);
        testLikeListController.removeLike(USER_ID_1, USER_ID_2);
    }
}
