package com.courseproject.tindar.usecases.likelist;


import java.util.ArrayList;
import java.util.Objects;

public class LikeListInteractor implements LikeListInputBoundary {
    /** This class checks if other is in userId likeList. If not, userId is added to other likeList.
     If yes, other is added to userId matchList and returns true. Return false otherwise.
     **/
    private final LikeListDsGateway likeListDsGateway;

    public LikeListInteractor(LikeListDsGateway likeListDsGateway) {
        this.likeListDsGateway = likeListDsGateway;
    }

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

    /**
     * removes otherUserId from userId like list and unmatches them if they are currently matched
     * @param userId
     * @param otherUserId
     */
    @Override
    public void removeLike(String userId, String otherUserId){
        // This method removes otherUserId from userId like list and unmatches them if they are
        // currently matched
        likeListDsGateway.removeLike(userId, otherUserId);
        likeListDsGateway.removeFromMatched(userId, otherUserId);

        // to satisfy removeFromMatched precondition: userId < otherUserId. This is needed because
        // matches table does not have duplicate records of (userId, otherUserId) and (otherUserId, userId) in the database
        // and want to make sure we delete the record with correct order the database would have
        if (Integer.parseInt(userId) < Integer.parseInt(otherUserId)) {
            likeListDsGateway.removeFromMatched(userId, otherUserId);
        } else {
            likeListDsGateway.removeFromMatched(otherUserId, userId);
        }
    }

    @Override
    public LikeListResponseModel getDisplayNamesForMatches(String userId) {
        // This method returns the display names if the users in the match list for display purposes
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

