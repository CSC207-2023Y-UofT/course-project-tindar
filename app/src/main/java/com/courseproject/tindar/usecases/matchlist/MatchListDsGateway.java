package com.courseproject.tindar.usecases.matchlist;

import java.util.ArrayList;
/**
 * interface for the data-saving/data-fetching gateway of Match List feature
 */
public interface MatchListDsGateway {

    /**
     * Read and return the userIds stored in a match list
     *
     * @param userId id of user match list we are reading
     * @return return list of userIds in the match list
     */
    ArrayList<String[]> readMatchList(String userId);

    /**
     * Read and return the display names of users stored in a match list
     *
     * @param userIds id of users in the match list
     * @return return list of display names in the match list
     */
    ArrayList<MatchListDsResponseModel> readDisplayNames(ArrayList<String> userIds);
}