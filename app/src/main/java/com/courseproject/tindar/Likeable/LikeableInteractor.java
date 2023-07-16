package com.courseproject.tindar.Likeable;
import java.util.ArrayList;

public class LikeableInteractor extends Account{
    // This class implements setter and getter functions for the Lists that contain different
    // user objects.

    public ArrayList<String> getBLockList(String id){
        return id.getBlockedList();
    }
    public ArrayList<String> getLikeList(String id) {
        return id.getLikeList();
    }
    public void addToBlocked(String id, String other) {
        id.addToBlocked(other);
    }
    public void addToMatched(String id, String other){
        id.addToMatched(other);
}
