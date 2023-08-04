package com.courseproject.tindar.controllers.likelist;

import com.courseproject.tindar.usecases.likelist.LikeListInputBoundary;

public class LikeListController {
/** This controller class accepts user input and connects it to back end likeList functions **/
    final LikeListInputBoundary userInput;

    public LikeListController(LikeListInputBoundary likeListUserInput){
        // User input
        this.userInput = likeListUserInput;
    }
    public void addLike(String userId, String otherUserId){
        // User input of userId liking otherUserId
        this.userInput.addLike(userId, otherUserId);
    }
    public void removeLike(String userId, String otherUserId){
        // User input of userId unliking otherUserId
        this.userInput.removeLike(userId, otherUserId);
    }
    public String[] getDisplayNamesForMatches(String userId){
        // Return display names of userId match list
        return this.userInput.getDisplayNamesForMatches(userId);
    }
}
