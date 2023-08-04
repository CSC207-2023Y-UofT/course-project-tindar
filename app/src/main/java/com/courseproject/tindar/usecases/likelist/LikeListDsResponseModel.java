package com.courseproject.tindar.usecases.likelist;

public class LikeListDsResponseModel {

    private final String userId;
    private final String displayName;

    public LikeListDsResponseModel(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {return userId;}

    public String getDisplayName() {
        return displayName;
    }
}
