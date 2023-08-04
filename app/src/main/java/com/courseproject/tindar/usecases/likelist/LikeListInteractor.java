package com.courseproject.tindar.usecases.likelist;

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

    @Override
    public void removeLike(String userId, String otherUserId){
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
}

