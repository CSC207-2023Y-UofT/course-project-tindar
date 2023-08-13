package com.courseproject.tindar.usecases.viewprofile;

/**
 * Interactor of the View Profile feature
 */
public class ViewProfileInteractor implements ViewProfileInputBoundary {

    /**
     * data-saving/data-fetching gateway of View Profile feature
     */
    final ViewProfileDsGateway viewProfileDsGateway;

    /**
     * constructs the interactor of the View Profile feature
     *
     * @param viewProfileDsGateway data-saving/data-fetching gateway of View Profile feature
     */
    public ViewProfileInteractor(ViewProfileDsGateway viewProfileDsGateway) {
        this.viewProfileDsGateway = viewProfileDsGateway;
    }

    /**
     * gets profile information of the user
     *
     * @param userId the user id of the account
     * @return profile information of the user
     */
    public ViewProfileResponseModel getProfile(String userId) {
        return viewProfileDsGateway.readProfile(userId);
    }
}
