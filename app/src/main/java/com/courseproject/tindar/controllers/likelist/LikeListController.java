package com.courseproject.tindar.controllers.likelist;

import com.courseproject.tindar.usecases.likelist.LikeListInputBoundary;
import com.courseproject.tindar.usecases.likelist.LikeListResponseModel;

/** This controller class accepts user input and connects it to back-end likeList functions **/
public class LikeListController {

    /** userInput is the users input, either 'like' or 'remove like' */
    final LikeListInputBoundary userInput;

    /**
     * Accept the users input to either 'like' or remove a 'like' of a profile.
     *
     * @param likeListUserInput the users input ('like' or 'remove like')
     */
    public LikeListController(LikeListInputBoundary likeListUserInput){
        // User input
        this.userInput = likeListUserInput;
    }

    /**
     * Accept the users input to like' a profile
     *
     * @param userId the users input to initiate 'like'
     * @param otherUserId the user being 'liked'
     */
    public void addLike(String userId, String otherUserId){
        // User input of userId liking otherUserId
        this.userInput.addLike(userId, otherUserId);
    }

    /**
     * Accept the users input to remove a 'like' of a profile
     *
     * @param userId the users input to remove 'like'
     * @param otherUserId the user being 'unliked'
     */
    public void removeLike(String userId, String otherUserId){
        // User input of userId unliking otherUserId
        this.userInput.removeLike(userId, otherUserId);
    }

    /**
     * Takes a user ID and returns the display names of those in their match list.
     *
     * @param userId the users match list we are returning
     * @return list of display names corresponding to the users in the match list.
     */
    public LikeListResponseModel getDisplayNamesForMatches(String userId){
        return this.userInput.getDisplayNamesForMatches(userId);
    }
}
