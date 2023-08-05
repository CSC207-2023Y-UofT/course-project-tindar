package com.courseproject.tindar.usecases.likelist;

public class LikeListDsResponseModel {
    /** This class provides a response gateway form the database when called to return userId and
     * match list display name information **/

    //TODO: userId will be used in the future implementation once user can click on the match and opens a chat window
    private final String userId;
    private final String displayName;

    public LikeListDsResponseModel(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() { return userId; }
    public String getDisplayName() {
        return displayName;
    }
}
