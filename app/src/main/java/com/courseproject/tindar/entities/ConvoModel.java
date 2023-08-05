package com.courseproject.tindar.entities;

public interface ConvoModel {

    //attributes:
    //profile pic
    //display name
    //last message
    //last message time

    // getter function
    //public String getProfilePic();
    public  String getDisplayName();
    public  String getLastMessageDisplayText();
    // MAKE SURE FORMAT IS ALWAYS YYYY-MM-DD HH:MM (24 HR)
    public  String getLastMessageTime();
}
