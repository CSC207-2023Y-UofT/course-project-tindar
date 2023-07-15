package com.courseproject.tindar.entities;

import java.util.ArrayList;
import java.util.Date;

class Account {
    boolean status;
    String id;
    String email;
    String password;
    String displayName;
    String firstName;
    String lastName;
    String aboutMe;
    String profilePictureLink;
    ArrayList<String> matchList;
    ArrayList<String> likeList;
    ArrayList<String> blockList;
    ArrayList<String> recentlyViewed;
    Date birthdate;
    Location location;
    Gender gender;
    Filters filters;

    Account(String id, String email, String password,
                   String display, String first, String last) {
        this.status = true;
        this.id = id;
        this.email = email;
        this.password = password;
        this.displayName = display;
        this.firstName = first;
        this.lastName = last;
        this.aboutMe = "";
        this.profilePictureLink = "";
        this.matchList = new ArrayList<>();
        this.likeList = new ArrayList<>();
        this.blockList = new ArrayList<>();
        this.recentlyViewed = new ArrayList<>();
        this.birthdate = null;
        this.location = null;
        this.gender = null;
        this.filters = null;
    }

}
