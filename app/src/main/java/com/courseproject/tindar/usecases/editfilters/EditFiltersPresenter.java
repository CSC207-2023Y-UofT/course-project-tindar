package com.courseproject.tindar.usecases.editfilters;

import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;

/**
 * interface for the presenter of the Edit Filters feature
 */
public interface EditFiltersPresenter {
    /**
     * prepares update filters fail view
     *
     * @param error error message
     * @throws InvalidAgeGroup always
     */
    void prepareFailView(String error) throws InvalidAgeGroup;
}
