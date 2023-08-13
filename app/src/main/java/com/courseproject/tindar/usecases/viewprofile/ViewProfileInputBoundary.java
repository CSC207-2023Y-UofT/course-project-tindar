package com.courseproject.tindar.usecases.viewprofile;

/**
 * interactor of the View Profile feature
 */
public interface ViewProfileInputBoundary {
    /**
     * gets profile information of the user
     *
     * @param userId the user id of the account
     * @return profile information of the user
     */
    ViewProfileResponseModel getProfile(String userId);
}

