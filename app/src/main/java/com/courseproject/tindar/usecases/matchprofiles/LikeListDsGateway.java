package com.courseproject.tindar.usecases.matchprofiles;

import java.util.ArrayList;

public interface LikeListDsGateway {

    ArrayList<String> readLikeList(String userId);

    void addToMatched(String userId, String otherUserId);

    void addLike(String userId, String otherUserId);
}
