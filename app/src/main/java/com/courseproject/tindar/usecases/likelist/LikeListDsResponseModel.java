package com.courseproject.tindar.usecases.likelist;

/** This class provides a response gateway from the database, when called, to return userId and
 * match list display name and userId information **/
public class LikeListDsResponseModel {
    private final String userId;
    private final String displayName;

    /**
     * Construct a LikeListDsResponseModel object
     *
     * @param userId current users userId
     * @param displayName display name of userId
     */
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
