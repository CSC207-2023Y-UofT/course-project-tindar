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
    public boolean addLike(String userId, String otherUserId){
        likeListDsGateway.addLike(userId, otherUserId);

        if (likeListDsGateway.checkLiked(otherUserId, userId)) {
            likeListDsGateway.addToMatched(userId, otherUserId);
            return true;
        }
        return false;
    }

    @Override
    public void removeLike(String userId, String otherUserId){
        likeListDsGateway.removeLike(userId, otherUserId);
        likeListDsGateway.removeFromMatched(userId, otherUserId);
    }
}

