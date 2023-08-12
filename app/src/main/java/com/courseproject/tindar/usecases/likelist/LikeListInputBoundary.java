package com.courseproject.tindar.usecases.likelist;

/**
 * This interface initializes a like interaction between two users. the current user userID
 * will be added to other likeList. In the event that userId is already in other likeList
 * they will be matched. removeLike Removes userId from otherUserId like list, and removes
 * the users from each others matchLists.
 **/
public interface LikeListInputBoundary {

    /**
     * check if user with userId like another user with otherUserId
     *
     * @param userId id of user who is checking to 'like' the other user
     * @param otherUserId userId of user is getting checked if 'liked'
     * @return true if user with userId 'likes' another user with otherUserId; false otherwise
     */
    boolean checkLiked(String userId, String otherUserId);

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
}

