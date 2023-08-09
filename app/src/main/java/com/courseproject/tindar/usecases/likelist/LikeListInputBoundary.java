package com.courseproject.tindar.usecases.likelist;

/**
 * This interface initializes a like interaction between two users. the current user userID
 * will be added to other likeList. In the event that userId is already in other likeList
 * they will be matched. removeLike Removes userId from otherUserId like list, and removes
 * the users from each others matchLists.
 **/
public interface LikeListInputBoundary {

    /**
     * Initiate a 'like' from userId to otherUserId
     *
     * @param userId id of user who is 'liking' the other user
     * @param otherUserId userId of user receiving a 'like'
     */
    void addLike(String userId, String otherUserId);

    /**
     * Remove a 'like' from userId to otherUserId
     *
     * @param userId id of user who is 'liked'' the other user
     * @param otherUserId userId of user who received a 'like'
     */
    void removeLike(String userId, String otherUserId);

    /**
     * Get display names of users matched with userId
     * @param userId id of user who we are retrieving the matched display names
     */
    LikeListResponseModel getDisplayNamesForMatches(String userId);
}

