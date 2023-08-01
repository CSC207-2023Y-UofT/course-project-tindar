package com.courseproject.tindar.usecases.editfilters;

import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;

public interface EditFiltersPresenter {
    void prepareFailView(String error) throws InvalidAgeGroup;
}
