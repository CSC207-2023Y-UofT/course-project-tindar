package com.courseproject.tindar.usecases.editfilters;

import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;

import java.util.ArrayList;

public interface EditFiltersInputBoundary {
    EditFiltersDsResponseModel getFilters(String userId);

    void updatePreferredGenders(String userId, ArrayList<String> preferredGenders);

    void updatePreferredLocations(String userId, ArrayList<String> preferredLocations);

    void updatePreferredAgeGroup(String userId, int minAge, int maxAge) throws InvalidAgeGroup;
}
