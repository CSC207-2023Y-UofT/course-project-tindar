package com.courseproject.tindar.usecases.userlist;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UserListInteractorUnitTest {
    private static final String USER_ID_1 = "1";
    private static final String USER_ID_2 = "2";
    private static final ArrayList<String> USERLIST = new ArrayList<>(Arrays.asList(USER_ID_1, USER_ID_2));

    private static class MockUserListDsGateway implements UserListDsGateway {

        @Override
        public ArrayList<String> getAllOtherUserIds(String userId) {
            return USERLIST;
        }
    }

    @Test
    public void getUserListOne() {
        MockUserListDsGateway userListDsGateway = new MockUserListDsGateway();
        assertEquals(USERLIST, userListDsGateway.getAllOtherUserIds(USER_ID_1));
    }

    @Test
    public void getUserListTwo() {
        MockUserListDsGateway userListDsGateway = new MockUserListDsGateway();
        assertEquals(USERLIST, userListDsGateway.getAllOtherUserIds(USER_ID_2));
    }

}
