package com.courseproject.tindar.usecases.likelist;

import java.util.ArrayList;

/**
 This interface accesses and distributes data from database to be manipulated by likeList functions
 * and allows for users to like and match with each other, documentation where methods are
 * implemented in DatabaseHelper
 */
public interface LikeListDsGateway {

    /**
     * check if user with userId likes user with otherUserId
     *
     * @param userId id of user who we are trying to check if he/she likes other user
     * @param otherUserId userId of user receiving a "like"
     * @return true if user likes other user; false otherwise.
     */
    boolean checkLiked(String userId, String otherUserId);

    void addToMatched(String userId, String otherUserId);

    void addLike(String userId, String otherUserId);

    void removeLike(String userId, String otherUserId);

    void removeFromMatched(String userId, String otherUserId);

    ArrayList<String[]> readMatchList(String userId);

    ArrayList<LikeListDsResponseModel> readDisplayNames(ArrayList<String> userIds);
}
