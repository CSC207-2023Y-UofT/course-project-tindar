package com.courseproject.tindar.usecases.matchlist;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import java.util.ArrayList;

public class MatchListInteractorUnitTest {
    /** This class tests the implementation and return values of MatchListInteractor **/

    private static final String USER_ID_1 = "1";
    private static final String USER_ID_2 = "2";
    private static final String USER_DISPLAY_NAME_1 = "rogers";
    private static final String USER_DISPLAY_NAME_2 = "bell";
    private static final String[] MATCH = new String[]{USER_ID_1, USER_ID_2};
    private static final MatchListDsResponseModel DISPLAY_NAME_1 = new MatchListDsResponseModel(USER_ID_1, USER_DISPLAY_NAME_1);
    private static final MatchListDsResponseModel DISPLAY_NAME_2 = new MatchListDsResponseModel(USER_ID_2, USER_DISPLAY_NAME_2);

    private static class MockMatchListDsGateway implements MatchListDsGateway {
        /** Mock implementation of MatchListDsGateway for testing purposes **/
        boolean isLiked;
        ArrayList<String> matchList;
        ArrayList<String> likeList;
        MatchListDsResponseModel displayName;
        public MockMatchListDsGateway(boolean isLiked, ArrayList<String> matchList,
                                     ArrayList<String> likeList, MatchListDsResponseModel displayName) {
            this.isLiked = isLiked;
            this.matchList = matchList;
            this.likeList = likeList;
            this.displayName = displayName;
        }

        @Override
        public ArrayList<String[]> readMatchList(String userId) {
            // Fake match list for testing purposes
            ArrayList<String[]> matchList = new ArrayList<>();
            matchList.add(MATCH);
            return matchList;
        }

        @Override
        public ArrayList<MatchListDsResponseModel> readDisplayNames(ArrayList<String> userIds) {
            // Method that reads the display names of the userIds in the users match list
            ArrayList<MatchListDsResponseModel> displayNameList = new ArrayList<>();
            displayNameList.add(displayName);
            return displayNameList;
        }

    }

    @Test
    public void testGetDisplayNamesForMatches() {
        // Tests tht getDisplayNamesForMatches returns display names in userId match list
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        MatchListDsGateway matchListDsGateway = new MatchListInteractorUnitTest.MockMatchListDsGateway(true, matchList,
                likeList, DISPLAY_NAME_2);
        MatchListInteractor matchListInteractor = new MatchListInteractor(matchListDsGateway);
        MatchListResponseModel matchedUsers = matchListInteractor.getDisplayNamesForMatches(USER_ID_1);
        assertArrayEquals(new String[]{USER_DISPLAY_NAME_2}, matchedUsers.getDisplayNames());
        assertArrayEquals(new String[]{USER_ID_2}, matchedUsers.getUserIds());
    }

    @Test
    public void testGetDisplayNamesForMatchesReversed() {
        // Tests tht getDisplayNamesForMatches returns display names in otherUserId match list
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        MatchListDsGateway matchListDsGateway = new MatchListInteractorUnitTest.MockMatchListDsGateway(true, matchList,
                likeList, DISPLAY_NAME_1);
        MatchListInteractor matchListInteractor = new MatchListInteractor(matchListDsGateway);
        MatchListResponseModel matchedUsers = matchListInteractor.getDisplayNamesForMatches(USER_ID_2);
        assertArrayEquals(new String[]{USER_DISPLAY_NAME_1}, matchedUsers.getDisplayNames());
        assertArrayEquals(new String[]{USER_ID_1}, matchedUsers.getUserIds());
    }
}
