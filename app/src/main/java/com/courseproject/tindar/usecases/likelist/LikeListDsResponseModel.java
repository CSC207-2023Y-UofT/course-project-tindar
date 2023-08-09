package com.courseproject.tindar.usecases.likelist;

public class LikeListDsResponseModel {
    /** This class provides a response gateway from the database, when called, to return userId and
     * match list display name and userId information **/

    private final String userId;
    private final String displayName;

    public LikeListDsResponseModel(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }
    // simple getter functions
    /**
     * Return userId
     */
    public String getUserId() { return userId; }

    /**
     * Return users display name
     */
    public String getDisplayName() { return displayName; }
}
