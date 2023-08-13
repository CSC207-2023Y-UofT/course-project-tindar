package com.courseproject.tindar.usecases.matchlist;

/** This class allows the match list fragment to simultaneously display users displayNames and
 * send the userIds to ChatActivity so when a  user is clicked in the match list, we know which
 * conversation to transition to.
 */
public class MatchListResponseModel {

    /** List of userIds */
    private final String[] userIds;

    /** List of displayNames */
    private final String[] displayNames;

    /**
     * Construct a MatchListResponseModel object
     *
     * @param userIds list of userIds in match list
     * @param displayNames list of displayNames in match list
     */
    public MatchListResponseModel(String[] userIds, String[] displayNames) {
        this.userIds = userIds;
        this.displayNames = displayNames;
    }
    // Two simple getter functions

    /** gets userIds
     * @return return userIds
     */
    public String[] getUserIds() {
        return userIds;
    }

    /** gets displayNames
     *@return  return displayNames
     */
    public String[] getDisplayNames() {
        return displayNames;
    }
}
