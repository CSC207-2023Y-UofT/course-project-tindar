package com.courseproject.tindar.controllers.likelist;

import com.courseproject.tindar.usecases.likelist.LikeListInputBoundary;

public class LikeListController {

    final LikeListInputBoundary userInput;

    public LikeListController(LikeListInputBoundary likeListUserInput){
        this.userInput = likeListUserInput;
    }
    public boolean addLike(String userId, String otherUserId){
        return userInput.addLike(userId, otherUserId);
    }
    void removeLike(String userId, String otherUserId){
        userInput.removeLike(userId, otherUserId);
    }
}
