package com.courseproject.tindar.usecases.likelist;

public class LikeListResponseModel {
    private final String[] userIds;
    private final String[] displayNames;

    public LikeListResponseModel(String[] userIds, String[] displayNames) {
        this.userIds = userIds;
        this.displayNames = displayNames;
    }
    // Two simple getter functions
    public String[] getUserIds() {
        return userIds;
    }

    public String[] getDisplayNames() {
        return displayNames;
    }
}
