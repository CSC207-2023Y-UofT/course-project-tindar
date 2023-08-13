package com.courseproject.tindar.usecases.viewprofile;

/**
 * interface for the data-saving/data-fetching gateway of View Profile feature
 */
public interface ViewProfileDsGateway {
    /**
     * reads profile information of the user
     *
     * @param userId the user id of the account
     * @return profile information of the user
     */
    ViewProfileResponseModel readProfile(String userId);
}
