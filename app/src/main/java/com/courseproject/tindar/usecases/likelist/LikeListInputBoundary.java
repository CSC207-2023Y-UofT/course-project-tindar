package com.courseproject.tindar.usecases.likelist;

public interface LikeListInputBoundary {
    /**
     * This interface initializes a like interaction between two users. the current user userID
     * will be added to other likeList. In the event that userId is already in other likeList
     * they will be matched. removeLike Removes userId from otherUserId like list, and removes
     * the users from each others matchLists
     **/
    boolean addLike(String userId, String otherUserId);

    void removeLike(String userId, String otherUserId);
}

