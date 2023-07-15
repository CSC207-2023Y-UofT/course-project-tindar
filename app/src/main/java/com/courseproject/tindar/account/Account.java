package com.courseproject.tindar.account;

import java.util.ArrayList;

class Account {
    int status;
    String id;
    String email;
    String password;
    String displayName;
    String firstName;
    String lastName;
    String aboutMe;
    String profilePictureLink;
    String birthdate;
    ArrayList<String> matchList;
    ArrayList<String> likeList;
    ArrayList<String> blockList;
    ArrayList<String> recentlyViewed;
    Location location;
    Gender gender;
    Filters filters;

    Account(String id, String email, String password,
                   String display, String first, String last) {
        this.status = 1;
        this.id = id;
        this.email = email;
        this.password = password;
        this.displayName = display;
        this.firstName = first;
        this.lastName = last;
        this.aboutMe = "";
        this.profilePictureLink = "";
        this.birthdate = "";
        this.matchList = new ArrayList<>();
        this.likeList = new ArrayList<>();
        this.blockList = new ArrayList<>();
        this.recentlyViewed = new ArrayList<>();
        this.location = null;
        this.gender = null;
        this.filters = null;
    }
}
