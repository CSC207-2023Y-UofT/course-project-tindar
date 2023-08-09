package com.courseproject.tindar.usecases.likelist;

import java.util.ArrayList;

/**
 This interface accesses and distributes data from database to be manipulated by likeList functions
 * and allows for users to like and match with each other, documentation where methods are
 * implemented in DatabaseHelper
 */
public interface LikeListDsGateway {

    /**
     * Check if user with userId likes user with otherUserId
     *
     * @param userId id of user who we are trying to check if he/she likes other user
     * @param otherUserId userId of user receiving a "like"
     * @return true if user likes other user; false otherwise.
     */
    boolean checkLiked(String userId, String otherUserId);

    /**
     * Add a match between userId and otherUserId to the database
     *
     * @param userId id of user who we matching with otherUserId
     * @param otherUserId same
     */
    void addToMatched(String userId, String otherUserId);

    /**
     * Add a 'like' between userId and otherUserId to the database
     *
     * @param userId id of user who 'liked' otherUserId
     * @param otherUserId userId of user receiving a 'like'
     */
    void addLike(String userId, String otherUserId);

    /**
     * Remove a 'like' between userId and otherUserId from the database
     *
     * @param userId id of user who we are trying to check if he/she likes other user
     * @param otherUserId userId of user receiving a "like"
     */
    void removeLike(String userId, String otherUserId);

    /**
     * Remove a 'match' between userId and otherUserId from the database
     *
     * @param userId id of user who 'unmatched' otherUserId
     * @param otherUserId userId of user who is being 'unmatched'
     */
    void removeFromMatched(String userId, String otherUserId);

    /**
     * Read and return the userIds stored in a match list
     *
     * @param userId id of user match list we are reading
     * @return ArrayList<String[]> of userIds in the match list
     */
    ArrayList<String[]> readMatchList(String userId);

    /**
     * Read and return the display names of users stored in a match list
     *
     * @param userIds id of users in the match list
     * @return ArrayList<LikeListDsResponseModel> of display names in the match list
     */
    ArrayList<LikeListDsResponseModel> readDisplayNames(ArrayList<String> userIds);
}
