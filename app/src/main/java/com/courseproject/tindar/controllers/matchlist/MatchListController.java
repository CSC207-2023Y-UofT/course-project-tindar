package com.courseproject.tindar.controllers.matchlist;

import com.courseproject.tindar.usecases.matchlist.MatchListInputBoundary;
import com.courseproject.tindar.usecases.matchlist.MatchListResponseModel;


/**
 * controller for the Match List UI
 */
public class MatchListController {
    /**
     * interactor of the Match List feature
     */
    final MatchListInputBoundary userInput;

    /**
     * Constructs interactor of the Match List feature
     *
     * @param likeListUserInput interactor of the Match List feature
     */
    public MatchListController(MatchListInputBoundary likeListUserInput){
        // User input
        this.userInput = likeListUserInput;
    }

    /**
     * Takes a user ID and returns the display names of those in their match list.
     *
     * @param userId the users match list we are returning
     * @return list of display names corresponding to the users in the match list.
     */
    public MatchListResponseModel getDisplayNamesForMatches(String userId){
        return this.userInput.getDisplayNamesForMatches(userId);
    }
}
