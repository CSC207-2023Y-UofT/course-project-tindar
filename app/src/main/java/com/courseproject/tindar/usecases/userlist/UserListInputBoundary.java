package com.courseproject.tindar.usecases.userlist;

import java.util.ArrayList;

/**
 * This interface defines the input boundary for the user list use case.
 * It provides a method to retrieve a list of user IDs excluding the provided user's ID.
 */
public interface UserListInputBoundary {


    /**
     * Retrieves a list of user IDs excluding the provided user's ID.
     *
     * @param userId The ID of the user for whom the list is being generated.
     * @return An ArrayList containing the user IDs of other users, excluding the provided user's ID.
     */
    ArrayList<String> getAllOtherUserIds(String userId);
}
