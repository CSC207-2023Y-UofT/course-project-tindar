package com.courseproject.tindar.usecases.editfilters;

import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;

/**
 * interface for the interactor of the Edit Filters feature
 */
public interface EditFiltersInputBoundary {
    /**
     * gets filters information of the user
     *
     * @param userId the user id of the account
     * @return filters information of the user
     */
    EditFiltersModel getFilters(String userId);

    /**
     * updates filters information of the user in the ds layer. If the age group is invalid throws InvalidAgeGroup
     * exception.
     *
     * @param userId the user id of the account
     * @param newFilters new filters information of the user to be updated
     * @throws InvalidAgeGroup if the age group provided is invalid
     */
    void updateFilters(String userId, EditFiltersModel newFilters) throws InvalidAgeGroup;
}
