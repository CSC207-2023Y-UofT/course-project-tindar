package com.courseproject.tindar.usecases.likelist;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;

public class LikeListInteractorUnitTest {
    /** This class tests the implementation and return values of LikeListInteractor **/

    private static final String USER_ID_1 = "1";
    private static final String USER_ID_2 = "2";
    private static final String USER_DISPLAY_NAME_1 = "rogers";
    private static final String USER_DISPLAY_NAME_2 = "bell";
    private static final String[] MATCH = new String[]{USER_ID_1, USER_ID_2};
    private static final LikeListDsResponseModel DISPLAY_NAME_1 = new LikeListDsResponseModel(USER_ID_1, USER_DISPLAY_NAME_1);
    private static final LikeListDsResponseModel DISPLAY_NAME_2 = new LikeListDsResponseModel(USER_ID_2, USER_DISPLAY_NAME_2);

    private static class MockLikeListDsGateway implements LikeListDsGateway {
        /** Mock implementation of LikeListDsGateway for testing purposes **/
        boolean isLiked;
        ArrayList<String> matchList;
        ArrayList<String> likeList;
        LikeListDsResponseModel displayName;
        public MockLikeListDsGateway(boolean isLiked, ArrayList<String> matchList,
                                     ArrayList<String> likeList, LikeListDsResponseModel displayName) {
            this.isLiked = isLiked;
            this.matchList = matchList;
            this.likeList = likeList;
            this.displayName = displayName;
        }

        @Override
        public boolean checkLiked(String userId, String otherUserId) {
            // Method to check if userId is liked by otherUserId
            return isLiked;
        }

        @Override
        public void addToMatched(String userId, String otherUserId) {
            // Method to add userId and otherUserId to each others match lists
            matchList.add(userId + " is matched " + otherUserId);
        }

        @Override
        public void addLike(String userId, String otherUserId) {
            // Method to add userId as a 'like' to otherUserId profile
            likeList.add(userId + " likes " + otherUserId);
        }

        @Override
        public void removeLike(String userId, String otherUserId) {
            // Method for userId to 'unlike' otherUserId
            likeList.remove(userId + " likes " + otherUserId);
        }

        @Override
        public void removeFromMatched(String userId, String otherUserId) {
            // Method that unmatches userId and otherUserId
            matchList.remove(userId + " is matched " + otherUserId);
        }

        @Override
        public ArrayList<String[]> readMatchList(String userId) {
            // Fake match list for testing purposes
            ArrayList<String[]> matchList = new ArrayList<>();
            matchList.add(MATCH);
            return matchList;
        }

        @Override
        public ArrayList<LikeListDsResponseModel> readDisplayNames(ArrayList<String> userIds) {
            // Method that reads the display names of the userIds in the users match list
            ArrayList<LikeListDsResponseModel> displayNameList = new ArrayList<>();
            displayNameList.add(displayName);
            return displayNameList;
        }

    }

    @Test
    public void addLikeAndMatchedWithFirstUserIdLargerThanSecondUserId() {
        // Test that USER_ID_1 and USER_ID_2 are matched when both users "like" each other
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(true, matchList,
                likeList, DISPLAY_NAME_1);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_1, USER_ID_2);
        assertEquals(USER_ID_1 + " likes " + USER_ID_2, likeList.get(0));
        assertEquals(USER_ID_1 + " is matched " + USER_ID_2, matchList.get(0));
    }

    @Test
    public void addLikeAndMatchedWithSecondUserIdLargerThanFirstUserId() {
        // Test that USER_ID_1 and USER_ID_2 are matched when both users "like" each other
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(true, matchList,
                likeList, DISPLAY_NAME_1);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_2, USER_ID_1);
        assertEquals(USER_ID_2 + " likes " + USER_ID_1, likeList.get(0));
        assertEquals(USER_ID_1 + " is matched " + USER_ID_2, matchList.get(0));
    }

    @Test
    public void addLikeAndNotMatched() {
        // Test that USER_ID_1 is added to USER_ID_2 likeList, but not match the users.
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(false, matchList,
                likeList, DISPLAY_NAME_1);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_1, USER_ID_2);
        assertEquals(USER_ID_1 + " likes " + USER_ID_2, likeList.get(0));
        assertTrue(matchList.isEmpty());
    }

    @Test
    public void removeLikeAndMatchedWithFirstUserIdLargerThanSecondUserId() {
        // Test that MOCK_USER_ID unlikes MOCK_OTHER_USER_ID, and that the users are also
        // unmatched
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(true, matchList,
                likeList, DISPLAY_NAME_1);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_1, USER_ID_2);
        assertEquals(USER_ID_1 + " likes " + USER_ID_2, likeList.get(0));
        assertEquals(USER_ID_1 + " is matched " + USER_ID_2, matchList.get(0));
        likeListInteractor.removeLike(USER_ID_1, USER_ID_2);
        assertTrue(likeList.isEmpty());
        assertTrue(matchList.isEmpty());
    }

    @Test
    public void removeLikeAndMatchedWithSecondUserIdLargerThanSecondUserId() {
        // Test that MOCK_USER_ID unlikes MOCK_OTHER_USER_ID, and that the users are also
        // unmatched.
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(true, matchList,
                likeList, DISPLAY_NAME_1);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_2, USER_ID_1);
        assertEquals(USER_ID_2 + " likes " + USER_ID_1, likeList.get(0));
        assertEquals(USER_ID_1 + " is matched " + USER_ID_2, matchList.get(0));
        likeListInteractor.removeLike(USER_ID_2, USER_ID_1);
        assertTrue(likeList.isEmpty());
        assertTrue(matchList.isEmpty());
    }

    @Test
    public void removeLikeAndNotMatched() {
        // Test removing a like where the two users are not matched
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(false, matchList,
                likeList, DISPLAY_NAME_1);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_1, USER_ID_2);
        assertEquals(USER_ID_1 + " likes " + USER_ID_2, likeList.get(0));
        assertTrue(matchList.isEmpty());
        likeListInteractor.removeLike(USER_ID_1, USER_ID_2);
        assertTrue(likeList.isEmpty());
    }

    @Test
    public void testGetDisplayNamesForMatches() {
        // Tests tht getDisplayNamesForMatches returns display names in userId match list
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(true, matchList,
                likeList, DISPLAY_NAME_2);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        LikeListResponseModel matchedUsers = likeListInteractor.getDisplayNamesForMatches(USER_ID_1);
        assertArrayEquals(new String[]{USER_DISPLAY_NAME_2}, matchedUsers.getDisplayNames());
        assertArrayEquals(new String[]{USER_ID_2}, matchedUsers.getUserIds());
    }

    @Test
    public void testGetDisplayNamesForMatchesReversed() {
        // Tests tht getDisplayNamesForMatches returns display names in otherUserId match list
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(true, matchList,
                likeList, DISPLAY_NAME_1);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        LikeListResponseModel matchedUsers = likeListInteractor.getDisplayNamesForMatches(USER_ID_2);
        assertArrayEquals(new String[]{USER_DISPLAY_NAME_1}, matchedUsers.getDisplayNames());
        assertArrayEquals(new String[]{USER_ID_1}, matchedUsers.getUserIds());
    }
}

