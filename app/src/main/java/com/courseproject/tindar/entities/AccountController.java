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
    /** Returns the password of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the password associated with the account
     */
    default String getPassword(Account account) {
        return account.password;
    }
    /** Returns the display name of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the display name associated with the account
     */
    default String getDisplayName(Account account) {
        return account.displayName;
    }
    /** Returns the first name of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the first name associated with the account
     */
    default String getFirstName(Account account) {
        return account.firstName;
    }
    /** Returns the last name of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the last name associated with the account
     */
    default String getLastName(Account account) {
        return account.lastName;
    }
    /** Returns the about me section of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the about me section associated with the account
     */
    default String getAboutMe(Account account) {
        return account.aboutMe;
    }
    /** Returns the link to the profile image of an account in the form of a String.
     *
     * @param account the account the function is reading
     * @return the link to the profile image associated with the account
     */
    default String getProfilePictureLink(Account account) {
        return account.profilePictureLink;
    }
    /** Returns the match list of an account in the form of an ArrayList.
     *
     * @param account the account the function is reading
     * @return the user id associated with the account
     */
    default ArrayList<String> getMatchList(Account account) {
        return account.matchList;
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