package com.courseproject.tindar.usecases.likelist;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;


public class LikeListInteractorUnitTest {
    /** This class tests the implementation and return values of LikeListInteractor **/

    private static final String USER_ID_1 = "user1";
    private static final String USER_ID_2 = "user2";

    private class MockLikeListDsGateway implements LikeListDsGateway {
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
            return isLiked;
        }

        @Override
        public void addToMatched(String MOCK_USER_ID, String MOCK_OTHER_USER_ID) {
            matchList.add(MOCK_USER_ID + " is matched " + MOCK_OTHER_USER_ID);
        }

        @Override
        public void addLike(String MOCK_USER_ID, String MOCK_OTHER_USER_ID) {
            likeList.add(MOCK_USER_ID + " likes " + MOCK_OTHER_USER_ID);
        }
    }
    @Test
    public void addLikeAndMatched(){
        /** Test that USER_ID_1 and USER_ID_2 are matched when both users "like" each other **/
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
    public void addLikeAndNotMatched(){
        /** Test that USER_ID_1 is added to USER_ID_2 likeList, but not match the users **/
        ArrayList<String> matchList = new ArrayList<>();
        ArrayList<String> likeList = new ArrayList<>();
        LikeListDsGateway likeListDsGateway = new MockLikeListDsGateway(false, matchList,
                likeList);

        LikeListInteractor likeListInteractor = new LikeListInteractor(likeListDsGateway);
        likeListInteractor.addLike(USER_ID_1, USER_ID_2);
        assertEquals(USER_ID_1 + " likes " + USER_ID_2, likeList.get(0));
        assertTrue(matchList.isEmpty());
    }

}

