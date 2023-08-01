package com.courseproject.tindar.controllers.editfilters;

import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;
import com.courseproject.tindar.usecases.editfilters.EditFiltersInputBoundary;
import com.courseproject.tindar.usecases.editfilters.EditFiltersDsResponseModel;

import java.util.ArrayList;

public class EditFiltersController {
    final EditFiltersInputBoundary userInput;

    public EditFiltersController(EditFiltersInputBoundary editFiltersUserInput) {
        this.userInput = editFiltersUserInput;
    }

    public EditFiltersDsResponseModel getFilters(String userId) {
        return userInput.getFilters(userId);
    }

    public void updatePreferredGenders(String userId, ArrayList<String> preferredGenders) {
        userInput.updatePreferredGenders(userId, preferredGenders);
    }

    public void updatePreferredLocations(String userId, ArrayList<String> preferredLocations) {
        userInput.updatePreferredLocations(userId, preferredLocations);
    }

    public void updatePreferredAgeGroup(String userId, int minAge, int maxAge) throws InvalidAgeGroup {
        userInput.updatePreferredAgeGroup(userId, minAge, maxAge);
    }
}
