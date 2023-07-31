package com.courseproject.tindar.controllers.likelist;

import com.courseproject.tindar.usecases.likelist.LikeListInputBoundary;

public class LikeListController {

    final LikeListInputBoundary userInput;

    public LikeListController(LikeListInputBoundary likeListUserInput){
        this.userInput = likeListUserInput;
    }
    public void addLike(String userId, String otherUserId){
        userInput.addLike(userId, otherUserId);
    }
    public void removeLike(String userId, String otherUserId){
        userInput.removeLike(userId, otherUserId);
    }
}
