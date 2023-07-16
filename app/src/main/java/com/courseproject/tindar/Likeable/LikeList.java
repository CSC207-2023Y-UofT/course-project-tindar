package com.courseproject.tindar.Likeable;

public class LikeList implements Likeable{
    // this class checks if other is in id likeList. If not, other is added to id likeList.
    // If yes, other is added to id likeList and matchList and returns true. Return false otherwise.
    @Override
    public boolean addToList(String id, String other) {
        if (id.getLikeList().contains(other) && id.getLikeList().contains(other)) {
            id.addToMatched(other);
            other.addToMatched(id);
            id.addToLikes(other);
            return true;
        } else {
            id.addToLikes(other);
            return true;
        }
        return false;
    }
}
