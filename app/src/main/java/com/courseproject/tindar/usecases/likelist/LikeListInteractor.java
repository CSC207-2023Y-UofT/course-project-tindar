package com.courseproject.tindar.usecases.likelist;


import java.util.ArrayList;
import java.util.Objects;

/** This class checks if other is in userId likeList. If not, userId is added to other likeList.
 If yes, other is added to userId matchList. Implementation for LikeListInputBoundary interface.
 **/
public class LikeListInteractor implements LikeListInputBoundary {

    private final LikeListDsGateway likeListDsGateway;

    public LikeListInteractor(LikeListDsGateway likeListDsGateway) {
        this.likeListDsGateway = likeListDsGateway;
    }

    /** Implementation of addLike method from LikeListInputBoundary interface. Add that userId has 'liked'
     * otherUserId to database
     * @param userId id of user who we are trying to check if he/she likes other user
     * @param otherUserId userId of user receiving a "like"
     */
    @Override
    public void addLike(String userId, String otherUserId){
        // This method adds otherUserId to list of userId 'likes', if the two uusers already liked
        // each other, they are matched and added to match list
        likeListDsGateway.addLike(userId, otherUserId);

        if (likeListDsGateway.checkLiked(otherUserId, userId)) {
            // to satisfy addToMatched precondition: userId < otherUserId. This is to avoid
            // duplicate records of (userId, otherUserId) and (otherUserId, userId) in the database
            if (Integer.parseInt(userId) < Integer.parseInt(otherUserId)) {
                likeListDsGateway.addToMatched(userId, otherUserId);
            } else {
                likeListDsGateway.addToMatched(otherUserId, userId);
            }
        }
    }
    /** Implementation of removeLike method from LikeListInputBoundary interface. Remove that
     * userId has 'liked' otherUserId from database
     * @param userId id of user who removed the 'like'
     * @param otherUserId userId of user who received a 'like'
     */
    @Override
    public void removeLike(String userId, String otherUserId){
        // This method removes otherUserId from userId like list and unmatches them if they are
        // currently matched
        likeListDsGateway.removeLike(userId, otherUserId);
        likeListDsGateway.removeFromMatched(userId, otherUserId);

        // to satisfy removeFromMatched precondition: userId < otherUserId. This is needed because the
        // match table does not have duplicate records of (userId, otherUserId) and (otherUserId, userId) in the database
        // and need to make sure we delete the record with correct order the database would have
        if (Integer.parseInt(userId) < Integer.parseInt(otherUserId)) {
            likeListDsGateway.removeFromMatched(userId, otherUserId);
        } else {
            likeListDsGateway.removeFromMatched(otherUserId, userId);
        }
    }

    /** Implementation of getDisplayNamesForMatches method from LikeListInputBoundary interface.
     * Return two String[] lists. One containing all matched userIds, and one containing all
     * matched user displayNames.
     * @param userId id of user who we are retrieving the matched display names
     * @return return two lists, one or userIds and one of displayNames in match list
     */
    @Override
    public LikeListResponseModel getDisplayNamesForMatches(String userId) {
        // This method returns the display names if the users in the match list for front end purposes
        ArrayList<String[]> arrayListMatches = likeListDsGateway.readMatchList(userId);
        ArrayList<String> matches = new ArrayList<>();
        for (String[] arrayListMatch : arrayListMatches) {
            if (Objects.equals(arrayListMatch[0], userId)) {
                matches.add(arrayListMatch[1]);
            } else {
                matches.add(arrayListMatch[0]);
            }
        }

        ArrayList<LikeListDsResponseModel> matchedUsers = likeListDsGateway.readDisplayNames(matches);

        int numberOfMatches = matchedUsers.size();
        String[] displayNames = new String[numberOfMatches];
        String[] userIds = new String[numberOfMatches];
        for (int i = 0; i < numberOfMatches; i++) {
            displayNames[i] = matchedUsers.get(i).getDisplayName();
            userIds[i] = matchedUsers.get(i).getUserId();
        }

        return new LikeListResponseModel(userIds, displayNames);
    }
}

