package com.courseproject.tindar.usecases.matchlist;

import java.util.ArrayList;
import java.util.Objects;

/**
 * interactor of the Match List feature
 */
public class MatchListInteractor implements MatchListInputBoundary {

    /** matchListDsGateway for updating the database */
    private final MatchListDsGateway matchListDsGateway;

    /** Create MatchListInteractor object
     * @param matchListDsGateway MatchListDsGateway object*/
    public MatchListInteractor(MatchListDsGateway matchListDsGateway) {
        this.matchListDsGateway = matchListDsGateway;
    }

    /** Implementation of getDisplayNamesForMatches method from MatchListInputBoundary interface.
     * Return two String[] lists. One containing all matched userIds, and one containing all
     * matched user displayNames.
     * @param userId id of user who we are retrieving the matched display names
     * @return return two lists, one or userIds and one of displayNames in match list
     */
    @Override
    public MatchListResponseModel getDisplayNamesForMatches(String userId) {
        // This method returns the display names if the users in the match list for front end purposes
        ArrayList<String[]> arrayListMatches = matchListDsGateway.readMatchList(userId);
        ArrayList<String> matches = new ArrayList<>();
        for (String[] arrayListMatch : arrayListMatches) {
            if (Objects.equals(arrayListMatch[0], userId)) {
                matches.add(arrayListMatch[1]);
            } else {
                matches.add(arrayListMatch[0]);
            }
        }

        ArrayList<MatchListDsResponseModel> matchedUsers = matchListDsGateway.readDisplayNames(matches);

        int numberOfMatches = matchedUsers.size();
        String[] displayNames = new String[numberOfMatches];
        String[] userIds = new String[numberOfMatches];
        for (int i = 0; i < numberOfMatches; i++) {
            displayNames[i] = matchedUsers.get(i).getDisplayName();
            userIds[i] = matchedUsers.get(i).getUserId();
        }

        return new MatchListResponseModel(userIds, displayNames);
    }
}
