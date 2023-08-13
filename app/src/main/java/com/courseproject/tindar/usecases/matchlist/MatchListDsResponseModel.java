package com.courseproject.tindar.usecases.matchlist;

/** This class provides a response gateway from the database, when called, to return userId and
 * match list display name and userId information **/
public class MatchListDsResponseModel {

    /** The users userId */
    private final String userId;

    /** The users displayName */
    private final String displayName;

    /**
     * Construct a MatchListDsResponseModel object
     *
     * @param userId current users userId
     * @param displayName display name of userId
     */
    public MatchListDsResponseModel(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }
    // simple getter methods
    /**
     * gets the user's userId
     *
     * @return return users userId
     */
    public String getUserId() { return userId; }

    /**
     * gets the user's displayName
     *
     * @return return users displayName
     */
    public String getDisplayName() { return displayName; }
}
