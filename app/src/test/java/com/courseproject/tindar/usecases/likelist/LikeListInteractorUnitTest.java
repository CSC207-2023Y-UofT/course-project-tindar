package com.courseproject.tindar.usecases.likelist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        public boolean checkLiked(String MOCK_USER_ID, String MOCK_OTHER_USER_ID) {
            // Method to check if MOCK_USER_ID is liked by MOCK_OTHER_USER_ID
            return isLiked;
        }

        @Override
        public void addToMatched(String MOCK_USER_ID, String MOCK_OTHER_USER_ID) {
            // Method to add MOCK_USER_ID and MOCK_OTHER_USER_ID to each others match lists
            matchList.add(MOCK_USER_ID + " is matched " + MOCK_OTHER_USER_ID);
        }

        @Override
        public void addLike(String MOCK_USER_ID, String MOCK_OTHER_USER_ID) {
            // Method to add MOCK_USER_ID as a 'like' to MOCK_OTHER_USER_ID profile
            likeList.add(MOCK_USER_ID + " likes " + MOCK_OTHER_USER_ID);
        }

        @Override
        public void removeLike(String MOCK_USER_ID, String MOCK_OTHER_USER_ID) {
            // Method for MOCK_USER_ID to 'unlike' MOCK_OTHER_USER_ID
            likeList.remove(MOCK_USER_ID + " likes " + MOCK_OTHER_USER_ID);
        }

        @Override
        public void removeFromMatched(String MOCK_USER_ID, String MOCK_OTHER_USER_ID) {
            // Method that unmatches MOCK_USER_ID and MOCK_OTHER_USER_ID
            matchList.remove(MOCK_USER_ID + " is matched " + MOCK_OTHER_USER_ID);
        }

        @Override
        public ArrayList<String[]> readMatchList(String userId) {
            return null;
        }
        // Method that reads match list from database

        @Override
        public ArrayList<LikeListDsResponseModel> readDisplayNames(ArrayList<String> userIds) {
            // Method that reads the display names of the userIds in the users match list
            return null;
        }

    }

    @Test
    public void addLikeAndMatchedWithFirstUserIdLargerThanSecondUserId() {
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
    public void addLikeAndMatchedWithSecondUserIdLargerThanFirstUserId() {
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
    public void addLikeAndNotMatched() {
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
    public void removeLikeAndMatchedWithFirstUserIdLargerThanSecondUserId() {
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
    public void removeLikeAndMatchedWithSecondUserIdLargerThanSecondUserId() {
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
    public void removeLikeAndNotMatched() {
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

    @Test
    public void testGetDisplayNamesForMatches() {
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(true, matchList,
                likeList);
        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_1, USER_ID_2);
        assertEquals(USER_ID_1 + " likes " + USER_ID_2, likeList.get(0));
        assertEquals(USER_ID_1 + " is matched " + USER_ID_2, matchList.get(0));
//        String[] displayName = likeListInteractor.getDisplayNamesForMatches(USER_ID_1);
//        assertEquals(matchList.get(0), displayName[0]);
    }
}

