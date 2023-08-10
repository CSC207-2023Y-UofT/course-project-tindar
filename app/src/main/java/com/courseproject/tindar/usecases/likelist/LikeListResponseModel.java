package com.courseproject.tindar.usecases.likelist;

/** This class allows the match list fragment to simultaneously display users displayNames and
 * send the userIds to ChatActivity so when a  user is clicked in the match list, we know which
 * conversation to transition to.
 */
public class LikeListResponseModel {

    /** List of userIds */
    private final String[] userIds;

    /** List of displayNames */
    private final String[] displayNames;

    /**
     * Construct a LikeListResponseModel object
     *
     * @param userIds list of userIds in match list
     * @param displayNames list of displayNames in match list
     */
    public LikeListResponseModel(String[] userIds, String[] displayNames) {
        this.userIds = userIds;
        this.displayNames = displayNames;
    }
    // Two simple getter functions

    /**
     * @return return userIds in the users match list
     */
    public String[] getUserIds() {
        return userIds;
    }

    /**
     *@return  return users display names in the users match list
     */
    public String[] getDisplayNames() {
        return displayNames;
    }
}
