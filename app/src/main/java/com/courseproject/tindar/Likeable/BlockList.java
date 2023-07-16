package com.courseproject.tindar.Likeable;


public class BlockList implements Likeable {
// this class checks if the given id has ToBlock in the blockList. If not, ToBlock is added
// to id.blockList. Returns true if added, false if not.


    @Override
    public boolean addToList(String id, String toBlock) {
        if (!(id.getblocklist().contains(toBlock))) {
            id.addToBLocked(id);
            return true;
        }
        return false;
    }
}

