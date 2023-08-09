package com.courseproject.tindar.usecases.editfilters;

import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;

public interface EditFiltersInputBoundary {
    EditFiltersModel getFilters(String userId);
    void updateFilters(String userId, EditFiltersModel newFilters) throws InvalidAgeGroup;
}
