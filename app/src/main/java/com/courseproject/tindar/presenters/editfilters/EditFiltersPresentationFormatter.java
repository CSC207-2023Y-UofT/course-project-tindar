package com.courseproject.tindar.presenters.editfilters;

import com.courseproject.tindar.usecases.editfilters.EditFiltersPresenter;

public class EditFiltersPresentationFormatter implements EditFiltersPresenter {
    @Override
    public void prepareFailView(String error) throws InvalidAgeGroup {
        throw new InvalidAgeGroup(error);
    }
}
