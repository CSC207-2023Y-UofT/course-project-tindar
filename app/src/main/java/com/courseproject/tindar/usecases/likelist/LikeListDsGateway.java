package com.courseproject.tindar.usecases.likelist;

public interface LikeListDsGateway {

    boolean checkLiked(String userId, String otherUserId);

    void addToMatched(String userId, String otherUserId);

    void addLike(String userId, String otherUserId);

    void removeLike(String userId, String otherUserId);

    void removeFromMatched(String userId, String otherUserId);
}
