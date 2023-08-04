package com.courseproject.tindar.usecases.likelist;

public class LikeListDsResponseModel {
    /** This class provides a response gateway form the database when called to return userId and
     * match list display name information **/
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
