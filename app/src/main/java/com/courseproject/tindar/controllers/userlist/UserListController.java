package com.courseproject.tindar.controllers.userlist;

import com.courseproject.tindar.usecases.userlist.UserListInputBoundary;

import java.util.ArrayList;

/**
 * This class is responsible for handling user's input for
 * managing user lists and interactions with user data.
 */
public class UserListController {
    final UserListInputBoundary userInput;


    public UserListController(UserListInputBoundary userInput) {
        this.userInput = userInput;
    }

    /**
     * Retrieves a list of user IDs for all other users except the specified user.
     *
     * @param userId
     * @return
     */

    public ArrayList<String> getAllOtherUserIds(String userId){return userInput.getAllOtherUserIds(userId);}
}
