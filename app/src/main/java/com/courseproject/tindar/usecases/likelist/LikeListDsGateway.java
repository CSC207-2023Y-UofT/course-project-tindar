package com.courseproject.tindar.usecases.likelist;

import java.util.ArrayList;

public interface LikeListDsGateway {
/** This class accesses and distributes data from database to be manipulated by likeList functions
 * and allows for users to like and match with each other, documentation where methods are
 * implemented in DatabaseHelper**/
    boolean checkLiked(String userId, String otherUserId);

    void addToMatched(String userId, String otherUserId);

    void addLike(String userId, String otherUserId);

    void removeLike(String userId, String otherUserId);

    void removeFromMatched(String userId, String otherUserId);

    ArrayList<String[]> readMatchList(String userId);

    ArrayList<LikeListDsResponseModel> readDisplayNames(ArrayList<String> userIds);
}
