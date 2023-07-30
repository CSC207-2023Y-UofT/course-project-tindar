package com.courseproject.tindar.usecases.matchprofiles;

public interface LikeListInputBoundary {
    /**
     * This interface initializes a like interaction between two users. the current user userID
     * will be added to other likeList. In the event that userId is already in other likeList
     * they will be matched.
     **/
    boolean addLike(String userId, String other);
}

