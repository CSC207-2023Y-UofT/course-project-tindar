package com.courseproject.tindar.entities;

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
    private String location;
    private String gender;
    private Filters filters;
    /**
     * Initializes an Account object which is used to store information
     * about a user.
     *
     * @param id the back-end user id associated with this account
     * @param email the email associated with this account
     * @param password the password used to log in to this account
     * @param displayName the username chosen to represent this account
     * @param firstName the account owner's first name
     * @param lastName the account owner's last name
     */
    Account(String id, String email, String password,
                   String displayName, String firstName, String lastName) {
        this.isActiveStatus = true;
        this.id = id;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
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
    public String getAccountStatus() {
        if (this.isActiveStatus) {
            return "Active";
        }
        else {
            return "Suspended";
        }
    }
    /** Sets an account's status to newStatus
     *
     * @param isActiveStatus the new status of the account
     */
    public void setIsActiveStatus(boolean isActiveStatus) {
        this.isActiveStatus = isActiveStatus;
    }
    /** Returns the id of an account in the form of a String.
     *
     * @return the user id associated with the account
     */
    public String getId() {
        return this.id;
    }
    /** Returns the email of an account in the form of a String.
     *
     * @return the email associated with the account
     */
    public String getEmail() {
        return this.email;
    }
    /** Changes the email associated with the account.
     *
     * @param email the new email associated with the account
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /** Returns the password of an account in the form of a String.
     *
     * @return the password associated with the account
     */
    public String getPassword() {
        return this.password;
    }
    /** Changes the password associated with the account.
     *
     * @param password the new password associated with the account
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /** Returns the display name of an account in the form of a String.
     *
     * @return the display name associated with the account
     */
    public String getDisplayName() {
        return this.displayName;
    }
    /** Changes the display name associated with the account.
     *
     * @param displayName the new display name associated with the account
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    /** Returns the first name of an account in the form of a String.
     *
     * @return the first name associated with the account
     */
    public String getFirstName() {
        return this.firstName;
    }
    /** Changes the first name associated with the account.
     *
     * @param firstName the new first name associated with the account
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /** Returns the last name of an account in the form of a String.
     *
     * @return the last name associated with the account
     */
    public String getLastName() {
        return this.lastName;
    }
    /** Changes the last name associated with the account.
     *
     * @param lastName the new last name associated with the account
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /** Returns the about me section of an account in the form of a String.
     *
     * @return the about me section associated with the account
     */
    public String getAboutMe() {
        return this.aboutMe;
    }
    /** Changes the about me section of the account.
     *
     * @param aboutMe the new about me section of the account
     */
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
    /** Returns the link to the profile image of an account in the form of a String.
     *
     * @return the link to the profile image associated with the account
     */
    public String getProfilePictureLink() {
        return this.profilePictureLink;
    }
    /** Changes the profile picture associated with the account.
     *
     * @param profilePictureLink the link to the new profile picture associated with the account
     */
    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }
    /** Returns the match list of an account in the form of an ArrayList.
     *
     * @return the user id associated with the account
     */
    public ArrayList<String> getMatchList() {
        return this.matchList;
    }
    /** Adds the id of an account to this account's match list.
     *
     * @param matched the account being added to the match list
     */
    public void addToMatchList(String matched) {
        this.matchList.add(matched);
    }
    /** Removes the id of an account to this account's match list.
     *
     * @param matched the account being removed from the match list
     */
    public void removeFromMatchList(String matched) {
        this.matchList.remove(matched);
    }
    /** Returns the liked list of an account in the form of an ArrayList.
     *
     * @return the liked list associated with the account
     */
    public ArrayList<String> getLikeList() {
        return this.likeList;
    }
    /** Adds the id of an account to this account's like list.
     *
     * @param liked the account being added to the like list
     */
    public void addToLikeList(String liked) {
        this.likeList.add(liked);
    }
    /** Removes the id of an account to this account's like list.
     *
     * @param liked the account being removed from the like list
     */
    public void removeFromLikeList(String liked) {
        this.likeList.remove(liked);
    }
    /** Returns the block list of an account in the form of an ArrayList.
     *
     * @return the block list associated with the account
     */
    public ArrayList<String> getBlockList() {
        return this.blockList;
    }
    /** Adds the id of an account to this account's block list.
     *
     * @param blocked the account being added to the block list
     */
    public void addToBlockList(String blocked) {
        this.blockList.add(blocked);
    }
    /** Removes the id of an account to this account's block list.
     *
     * @param blocked the account being removed from the block list
     */
    public void removeFromBlockList(String blocked) {
        this.blockList.remove(blocked);
    }
    /** Returns a list of the ids of recently viewed profiles
     *  of an account in the form of an ArrayList.
     *
     * @return a list of recently viewed profiles associated with the account
     */
    public ArrayList<String> getRecentlyViewed() {
        return this.recentlyViewed;
    }
    /** Adds the id of an account to this account's recently viewed profiles.
     *
     * @param viewed the account being added to the recently viewed profiles
     */
    public void addToRecentlyViewed(String viewed) {
        this.recentlyViewed.add(viewed);
    }
    /** Removes the id of an account to this account's recently viewed profiles.
     *
     * @param viewed the account being removed from the recently viewed profiles
     */
    public void removeFromRecentlyViewed(String viewed) {
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
     * @param birthdate the new birthdate associated with the account
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    /** Returns a user's location from their account in the form of a String.
     *
     * @return the location associated with the account
     */
    public String getLocation() {
        return this.location;
    }
    /** Changes the location associated with the account.
     *
     * @param location the new location associated with the account
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /** Returns a user's gender from their account in the form of a String.
     *
     * @return the gender associated with the account
     */
    public String getGender() {
        return this.gender;
    }
    /** Changes the gender associated with the account.
     *
     * @param gender the new gender associated with the account
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /** Returns the filters of an account in the form of a Filters.
     *
     * @return the filters associated with the account
     */
    public Filters getFilters() {
        return this.filters;
    }
    // TODO: Create setFilters(). Requires Filters class to be created.

}
