package com.courseproject.tindar.entities;

import android.location.Location;

import java.util.ArrayList;
import java.util.Date;

public interface AccountController {
    /** Creates an account after receiving an email,
     * password, display name, first name, and last name.
     *
     * @param email the email to be associated with the account
     * @param password the password to be associated with the account
     * @param display the display name to be associated with the account
     * @param first the first name to be associated with the account
     * @param last the last name to be associated with the account
     */
    default void createAccount(String email, String password,
                               String display, String first, String last) {
        // TODO: need a way to iterate through ids, then convert to string
        new Account(id, email, password, display, first, last);
    }
    /** Returns the status of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the status of the account
     */
    default String getAccountStatus(Account account) {
        if (account.isActiveStatus) {
            return "Active";
        }
        else {
            return "Suspended";
        }
    }
    /** Flips the account status from Active to Suspended, or vice versa.
     *
     * @param account the account going to have its status changed
     */
    default void setAccountStatus(Account account) {
        account.isActiveStatus = !account.isActiveStatus;
    }
    /** Returns the id of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the user id associated with the account
     */
    default String getId(Account account) {
        return account.id;
    }
    /** Returns the email of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the email associated with the account
     */
    default String getEmail(Account account) {
        return account.email;
    }
    /** Changes the email associated with the account.
     *
     * @param account the account having its email changed
     * @param email the new email associated with the account
     */
    default void setEmail(Account account, String email) {
        account.email = email;
    }
    /** Returns the password of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the password associated with the account
     */
    default String getPassword(Account account) {
        return account.password;
    }
    /** Changes the password associated with the account.
     *
     * @param account the account having its password changed
     * @param password the new password associated with the account
     */
    default void setPassword(Account account, String password) {
        account.password = password;
    }
    /** Returns the display name of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the display name associated with the account
     */
    default String getDisplayName(Account account) {
        return account.displayName;
    }
    /** Changes the display name associated with the account.
     *
     * @param account the account having its display name changed
     * @param display the new display name associated with the account
     */
    default void setDisplayName(Account account, String display) {
        account.displayName = display;
    }
    /** Returns the first name of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the first name associated with the account
     */
    default String getFirstName(Account account) {
        return account.firstName;
    }
    /** Changes the first name associated with the account.
     *
     * @param account the account having its first name changed
     * @param first the new first name associated with the account
     */
    default void setFirstName(Account account, String first) {
        account.firstName = first;
    }
    /** Returns the last name of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the last name associated with the account
     */
    default String getLastName(Account account) {
        return account.lastName;
    }
    /** Changes the last name associated with the account.
     *
     * @param account the account having its last name changed
     * @param last the new last name associated with the account
     */
    default void setLastName(Account account, String last) {
        account.lastName = last;
    }
    /** Returns the about me section of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the about me section associated with the account
     */
    default String getAboutMe(Account account) {
        return account.aboutMe;
    }
    /** Changes the about me section of the account.
     *
     * @param account the account having its about me section changed
     * @param about the new about me section of the account
     */
    default void setAboutMe(Account account, String about) {
        account.aboutMe = about;
    }
    /** Returns the link to the profile image of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the link to the profile image associated with the account
     */
    default String getProfilePictureLink(Account account) {
        return account.profilePictureLink;
    }
    /** Changes the profile picture associated with the account.
     *
     * @param account the account having its profile picture changed
     * @param picture the link to the new profile picture associated with the account
     */
    default void setProfilePictureLink(Account account, String picture) {
        account.profilePictureLink = picture;
    }
    /** Returns the match list of an account in the form of an ArrayList.
     *
     * @param account the account the function is reading
     * @return the user id associated with the account
     */
    default ArrayList<String> getMatchList(Account account) {
        return account.matchList;
    }
    /** Adds the name? id? of an account to this account's match list.
     * TODO: figure out how match and block lists work
     *
     * @param account the account having its match list changed
     * @param matched the account being added to the match list
     */
    default void addToMatchList(Account account, String matched) {
        account.matchList.add(matched);
    }
    /** Returns the liked list of an account in the form of an ArrayList.
     *
     * @param account the account the function is reading
     * @return the liked list associated with the account
     */
    default ArrayList<String> getLikeList(Account account) {
        return account.likeList;
    }
    /** Returns the block list of an account in the form of an ArrayList.
     *
     * @param account the account the function is reading
     * @return the block list associated with the account
     */
    default ArrayList<String> getBlockList(Account account) {
        return account.blockList;
    }
    /** Returns a list of recently viewed profiles
     *  of an account in the form of an ArrayList.
     *
     * @param account the account the function is reading.
     * @return a list of recently viewed profiles associated with the account
     */
    default ArrayList<String> getRecentlyViewed(Account account) {
        return account.recentlyViewed;
    }
    /** Returns a user's birthdate from their account in the form of a Date.
     *
     * @param account the account the function is reading
     * @return the birthdate associated with the account
     */
    default Date getBirthdate(Account account) {
        return account.birthdate;
    }
    /** Returns a user's location from their account in the form of a Location.
     *
     * @param account the account the function is reading
     * @return the location associated with the account
     */
    default Location getLocation(Account account) {
        return account.location;
    }
    /** Returns a user's gender from their account in the form of a Gender.
     *
     * @param account the account the function is reading
     * @return the gender associated with the account
     */
    default Gender getGender(Account account) {
        return account.gender;
    }
    /** Returns the filters of an account in the form of a Filters.
     *
     * @param account the account the function is reading
     * @return the filters associated with the account
     */
    default Filters getFilters(Account account) {
        return account.filters;
    }
}