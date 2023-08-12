package com.courseproject.tindar.controllers.userlist;

import com.courseproject.tindar.usecases.userlist.UserListInputBoundary;

import java.util.ArrayList;

public class UserListController {
    final UserListInputBoundary userInput;


    public UserListController(UserListInputBoundary userInput) {
        this.userInput = userInput;
    }

    public ArrayList<String> getAllOtherUserIds(String userId){return userInput.getAllOtherUserIds(userId);}
}
