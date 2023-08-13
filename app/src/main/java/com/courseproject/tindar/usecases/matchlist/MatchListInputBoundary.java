package com.courseproject.tindar.usecases.matchlist;

/**
 * This interface gets display names for matched users
 **/
public interface MatchListInputBoundary{
    /**
     * Get display names of users matched with userId
     * @param userId id of user who we are retrieving the matched display names
     */
    MatchListResponseModel getDisplayNamesForMatches(String userId);
}
