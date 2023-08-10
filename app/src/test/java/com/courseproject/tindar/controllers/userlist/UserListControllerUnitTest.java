package com.courseproject.tindar.controllers.userlist;

import com.courseproject.tindar.usecases.userlist.UserListInputBoundary;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UserListControllerUnitTest {
    private static final ArrayList<String> userList = new ArrayList<String>(Arrays.asList("99", "1", "15"));

    private class MockUserListUserInput implements UserListInputBoundary {
        @Override
        public ArrayList<String> getAllUserIds() {
            return userList;
        }
    }

    @Test
    public void testGetAllUserIds(){
        MockUserListUserInput mockUserListUserInput = new MockUserListUserInput();
        assertEquals(userList, mockUserListUserInput.getAllUserIds());
    }
}
