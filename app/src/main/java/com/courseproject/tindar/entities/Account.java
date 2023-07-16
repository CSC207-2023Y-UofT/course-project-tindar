package com.courseproject.tindar.entities;

import android.location.Location;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    boolean isActiveStatus;
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

    /**
     * Initializes an Account object, which is used to store information
     * about a user.
     *
     * @param id the back-end user id associated with this account
     * @param email the email associated with this account
     * @param password the password used to log in to this account
     * @param display the username chosen to represent this account
     * @param first the account owner's first name
     * @param last the account owner's last name
     */
    Account(String id, String email, String password,
                   String display, String first, String last) {
        this.isActiveStatus = true;
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
