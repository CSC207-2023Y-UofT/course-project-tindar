package com.courseproject.tindar.entities;

import android.location.Location;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("FieldMayBeFinal")

public class Account {
    private boolean isActiveStatus;
    private final String id;
    private String email;
    private String password;
    private String displayName;
    private String firstName;
    private String lastName;
    private String aboutMe;
    private String profilePictureLink;
    private ArrayList<String> matchList;
    private ArrayList<String> likeList;
    private ArrayList<String> blockList;
    private ArrayList<String> recentlyViewed;
    private Date birthdate;
    private Location location;
    private Gender gender;
    private Filters filters;
    /**
     * Initializes an Account objectwhich is used to store information
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
    /** Returns the status of an account in the form of a String.
     *
     * @return the status of the account
     */
    String getAccountStatus() {
        if (this.isActiveStatus) {
            return "Active";
        }
        else {
            return "Suspended";
        }
    }
    /** Flips the account status from Active to Suspendedor vice versa.
     *
     */
    void setAccountStatus() {
        this.isActiveStatus = !this.isActiveStatus;
    }
    /** Returns the id of an account in the form of a String.
     *
     * @return the user id associated with the account
     */
    String getId() {
        return this.id;
    }
    /** Returns the email of an account in the form of a String.
     *
     * @return the email associated with the account
     */
    String getEmail() {
        return this.email;
    }
    /** Changes the email associated with the account.
     *
     * @param email the new email associated with the account
     */
    void setEmail(String email) {
        this.email = email;
    }
    /** Returns the password of an account in the form of a String.
     *
     * @return the password associated with the account
     */
    String getPassword() {
        return this.password;
    }
    /** Changes the password associated with the account.
     *
     * @param password the new password associated with the account
     */
    void setPassword(String password) {
        this.password = password;
    }
    /** Returns the display name of an account in the form of a String.
     *
     * @return the display name associated with the account
     */
    String getDisplayName() {
        return this.displayName;
    }
    /** Changes the display name associated with the account.
     *
     * @param display the new display name associated with the account
     */
    void setDisplayName(String display) {
        this.displayName = display;
    }
    /** Returns the first name of an account in the form of a String.
     *
     * @return the first name associated with the account
     */
    String getFirstName() {
        return this.firstName;
    }
    /** Changes the first name associated with the account.
     *
     * @param first the new first name associated with the account
     */
    void setFirstName(String first) {
        this.firstName = first;
    }
    /** Returns the last name of an account in the form of a String.
     *
     * @return the last name associated with the account
     */
    String getLastName() {
        return this.lastName;
    }
    /** Changes the last name associated with the account.
     *
     * @param last the new last name associated with the account
     */
    void setLastName(String last) {
        this.lastName = last;
    }
    /** Returns the about me section of an account in the form of a String.
     *
     * @return the about me section associated with the account
     */
    String getAboutMe() {
        return this.aboutMe;
    }
    /** Changes the about me section of the account.
     *
     * @param about the new about me section of the account
     */
    void setAboutMe(String about) {
        this.aboutMe = about;
    }
    /** Returns the link to the profile image of an account in the form of a String.
     *
     * @return the link to the profile image associated with the account
     */
    String getProfilePictureLink() {
        return this.profilePictureLink;
    }
    /** Changes the profile picture associated with the account.
     *
     * @param picture the link to the new profile picture associated with the account
     */
    void setProfilePictureLink(String picture) {
        this.profilePictureLink = picture;
    }
    /** Returns the match list of an account in the form of an ArrayList.
     *
     * @return the user id associated with the account
     */
    ArrayList<String> getMatchList() {
        return this.matchList;
    }
    /** Adds the id of an account to this account's match list.
     *
     * @param matched the account being added to the match list
     */
    void addToMatchList(String matched) {
        this.matchList.add(matched);
    }
    /** Removes the id of an account to this account's match list.
     *
     * @param matched the account being removed from the match list
     */
    void removeFromMatchList(String matched) {
        this.matchList.remove(matched);
    }
    /** Returns the liked list of an account in the form of an ArrayList.
     *
     * @return the liked list associated with the account
     */
    ArrayList<String> getLikeList() {
        return this.likeList;
    }
    /** Adds the id of an account to this account's like list.
     *
     * @param liked the account being added to the like list
     */
    void addToLikeList(String liked) {
        this.likeList.add(liked);
    }
    /** Removes the id of an account to this account's like list.
     *
     * @param liked the account being removed from the like list
     */
    void removeFromLikeList(String liked) {
        this.likeList.remove(liked);
    }
    /** Returns the block list of an account in the form of an ArrayList.
     *
     * @return the block list associated with the account
     */
    ArrayList<String> getBlockList() {
        return this.blockList;
    }
    /** Adds the id of an account to this account's block list.
     *
     * @param blocked the account being added to the block list
     */
    void addToBlockList(String blocked) {
        this.blockList.add(blocked);
    }
    /** Removes the id of an account to this account's block list.
     *
     * @param blocked the account being removed from the block list
     */
    void removeFromBlockList(String blocked) {
        this.blockList.remove(blocked);
    }
    /** Returns a list of the ids of recently viewed profiles
     *  of an account in the form of an ArrayList.
     *
     * @return a list of recently viewed profiles associated with the account
     */
    ArrayList<String> getRecentlyViewed() {
        return this.recentlyViewed;
    }
    /** Adds the id of an account to this account's recently viewed profiles.
     *
     * @param viewed the account being added to the recently viewed profiles
     */
    void addToRecentlyViewed(String viewed) {
        this.recentlyViewed.add(viewed);
    }
    /** Removes the id of an account to this account's recently viewed profiles.
     *
     * @param viewed the account being removed from the recently viewed profiles
     */
    void removeFromRecentlyViewed(String viewed) {
        this.recentlyViewed.remove(viewed);
    }
    /** Returns a user's birthdate from their account in the form of a Date.
     *
     * @return the birthdate associated with the account
     */
    Date getBirthdate() {
        return this.birthdate;
    }
    /** Changes the birthdate associated with the account.
     *
     * @param date the new birthdate associated with the account
     */
    void setEmail(Date date) {
        this.birthdate = date;
    }
    /** Returns a user's location from their account in the form of a Location.
     *
     * @return the location associated with the account
     */
    Location getLocation() {
        return this.location;
    }
    /** Changes the location associated with the account.
     *
     * @param location the new location associated with the account
     */
    void setLocation(Location location) {
        this.location = location;
    }
    /** Returns a user's gender from their account in the form of a Gender.
     *
     * @return the gender associated with the account
     */
    Gender getGender() {
        return this.gender;
    }
    // TODO: Create setGender(). Requires Gender class to be created.
    /** Returns the filters of an account in the form of a Filters.
     *
     * @return the filters associated with the account
     */
    Filters getFilters() {
        return this.filters;
    }
    // TODO: Create setFilters(). Requires Filters class to be created.

}
