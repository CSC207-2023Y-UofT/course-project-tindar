package com.courseproject.tindar.Likeable;

public interface Likeable {
    // A class that interacts with three lists in the User account; Like list, Block list and
    // Match List.
    String id;

    boolean addToList(String id, String other);
    };

}
