package com.courseproject.tindar.controllers.matchlist;

import static org.junit.Assert.assertEquals;

import com.courseproject.tindar.usecases.matchlist.MatchListInputBoundary;
import com.courseproject.tindar.usecases.matchlist.MatchListResponseModel;

import org.junit.Test;

public class MatchListControllerUnitTest {
    /**
     * This class tests the implementation and return values of MatchListController
     **/
    private static final String USER_ID_1 = "user1";

    public static class MockMatchListUserInput implements MatchListInputBoundary {
        /**
         * Mock implementation of MatchListInputBoundary for testing purposes
         **/
        @Override
        public MatchListResponseModel getDisplayNamesForMatches(String userId) {
            // Mock list of display names from match list
            assertEquals(USER_ID_1, userId);
            return new MatchListResponseModel(new String[]{}, new String[]{});
        }
    }

    @Test
    public void testGetDisplayNamesForMatches() {
        // Test that GetDisplayNamesForMatches is called
        MatchListInputBoundary testMatchListUserInput = new MatchListControllerUnitTest.MockMatchListUserInput();
        MatchListController testMatchListController = new MatchListController(testMatchListUserInput);
        testMatchListController.getDisplayNamesForMatches(USER_ID_1);
    }
}
