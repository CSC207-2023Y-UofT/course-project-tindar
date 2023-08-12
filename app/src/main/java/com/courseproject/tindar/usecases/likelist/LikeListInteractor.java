package com.courseproject.tindar.usecases.likelist;

/** This class checks if other is in userId likeList. If not, userId is added to other likeList.
 If yes, other is added to userId matchList. Implementation for LikeListInputBoundary interface.
 **/
public class LikeListInteractor implements LikeListInputBoundary {

    /** likeListDsGateway for updating the database */
    private final LikeListDsGateway likeListDsGateway;

/** Create LikeListInteractor object
 * @param likeListDsGateway LikeListDsGateway object*/
    public LikeListInteractor(LikeListDsGateway likeListDsGateway) {
        this.likeListDsGateway = likeListDsGateway;
    }

    /**
     * Implementation of checkLiked method from LikeListInputBoundary interface. Check if user with userId like
     * another user with otherUserId
     *
     * @param userId id of user who is checking to 'like' the other user
     * @param otherUserId userId of user is getting checked if 'liked'
     * @return true if user with userId 'likes' another user with otherUserId; false otherwise
     */
    @Override
    public boolean checkLiked(String userId, String otherUserId) {
        return likeListDsGateway.checkLiked(userId, otherUserId);
    }

    /** Implementation of addLike method from LikeListInputBoundary interface. Add that userId has 'liked'
     * otherUserId to database
     * @param userId id of user who we are trying to check if he/she likes other user
     * @param otherUserId userId of user receiving a "like"
     */
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
}

