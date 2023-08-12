package com.courseproject.tindar.presenters.editfilters;

import com.courseproject.tindar.usecases.editfilters.EditFiltersPresenter;

/**
 * Presentation Formatter for the Edit Filters feature
 */
public class EditFiltersPresentationFormatter implements EditFiltersPresenter {
    /**
     * prepares filters update fail view
     *
     * @param error error message
     * @throws InvalidAgeGroup always
     */
    @Override
    public void prepareFailView(String error) throws InvalidAgeGroup {
        throw new InvalidAgeGroup(error);
    }
}
