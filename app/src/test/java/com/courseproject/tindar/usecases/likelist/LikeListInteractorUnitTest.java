package com.courseproject.tindar.usecases.likelist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.courseproject.tindar.usecases.matchlist.MatchListDsResponseModel;

import org.junit.Test;

import java.util.ArrayList;

public class LikeListInteractorUnitTest {
    /** This class tests the implementation and return values of LikeListInteractor **/

    private static final String USER_ID_1 = "1";
    private static final String USER_ID_2 = "2";

    private static class MockLikeListDsGateway implements LikeListDsGateway {
        /** Mock implementation of LikeListDsGateway for testing purposes **/
        boolean isLiked;
        ArrayList<String> matchList;
        ArrayList<String> likeList;

        public MockLikeListDsGateway(boolean isLiked, ArrayList<String> matchList,
                                     ArrayList<String> likeList) {
            this.isLiked = isLiked;
            this.matchList = matchList;
            this.likeList = likeList;
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
    }

    @Test
    public void testCheckLiked() {
        // Test that USER_ID_1 likes USER_ID_2
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(true, matchList,
                likeList);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        boolean doesLike = likeListInteractor.checkLiked(USER_ID_1, USER_ID_2);
        assertTrue(doesLike);
    }

    @Test
    public void testAddLikeAndMatchedWithFirstUserIdLargerThanSecondUserId() {
        // Test that USER_ID_1 and USER_ID_2 are matched when both users "like" each other
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(true, matchList,
                likeList);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_1, USER_ID_2);
        assertEquals(USER_ID_1 + " likes " + USER_ID_2, likeList.get(0));
        assertEquals(USER_ID_1 + " is matched " + USER_ID_2, matchList.get(0));
    }

    @Test
    public void testAddLikeAndMatchedWithSecondUserIdLargerThanFirstUserId() {
        // Test that USER_ID_1 and USER_ID_2 are matched when both users "like" each other
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(true, matchList,
                likeList);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_2, USER_ID_1);
        assertEquals(USER_ID_2 + " likes " + USER_ID_1, likeList.get(0));
        assertEquals(USER_ID_1 + " is matched " + USER_ID_2, matchList.get(0));
    }

    @Test
    public void testAddLikeAndNotMatched() {
        // Test that USER_ID_1 is added to USER_ID_2 likeList, but not match the users.
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(false, matchList,
                likeList);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_1, USER_ID_2);
        assertEquals(USER_ID_1 + " likes " + USER_ID_2, likeList.get(0));
        assertTrue(matchList.isEmpty());
    }

    @Test
    public void testRemoveLikeAndMatchedWithFirstUserIdLargerThanSecondUserId() {
        // Test that MOCK_USER_ID unlikes MOCK_OTHER_USER_ID, and that the users are also
        // unmatched
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(true, matchList,
                likeList);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_1, USER_ID_2);
        assertEquals(USER_ID_1 + " likes " + USER_ID_2, likeList.get(0));
        assertEquals(USER_ID_1 + " is matched " + USER_ID_2, matchList.get(0));
        likeListInteractor.removeLike(USER_ID_1, USER_ID_2);
        assertTrue(likeList.isEmpty());
        assertTrue(matchList.isEmpty());
    }

    @Test
    public void testRemoveLikeAndMatchedWithSecondUserIdLargerThanSecondUserId() {
        // Test that MOCK_USER_ID unlikes MOCK_OTHER_USER_ID, and that the users are also
        // unmatched.
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(true, matchList,
                likeList);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_2, USER_ID_1);
        assertEquals(USER_ID_2 + " likes " + USER_ID_1, likeList.get(0));
        assertEquals(USER_ID_1 + " is matched " + USER_ID_2, matchList.get(0));
        likeListInteractor.removeLike(USER_ID_2, USER_ID_1);
        assertTrue(likeList.isEmpty());
        assertTrue(matchList.isEmpty());
    }

    @Test
    public void testRemoveLikeAndNotMatched() {
        // Test removing a like where the two users are not matched
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(false, matchList,
                likeList);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_1, USER_ID_2);
        assertEquals(USER_ID_1 + " likes " + USER_ID_2, likeList.get(0));
        assertTrue(matchList.isEmpty());
        likeListInteractor.removeLike(USER_ID_1, USER_ID_2);
        assertTrue(likeList.isEmpty());
    }
}

