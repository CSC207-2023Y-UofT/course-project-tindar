package com.courseproject.tindar.controllers.editfilters;

import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;
import com.courseproject.tindar.usecases.editfilters.EditFiltersInputBoundary;
import com.courseproject.tindar.usecases.editfilters.EditFiltersModel;

public class EditFiltersController {
    final EditFiltersInputBoundary userInput;

    public EditFiltersController(EditFiltersInputBoundary editFiltersUserInput) {
        this.userInput = editFiltersUserInput;
    }

    public EditFiltersModel getFilters(String userId) {
        return userInput.getFilters(userId);
    }

    public void updateFilters(String userId, EditFiltersModel newFilters) throws InvalidAgeGroup {
        userInput.updateFilters(userId, newFilters);
    }
}
