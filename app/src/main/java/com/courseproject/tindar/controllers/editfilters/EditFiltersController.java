package com.courseproject.tindar.controllers.editfilters;

import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;
import com.courseproject.tindar.usecases.editfilters.EditFiltersInputBoundary;
import com.courseproject.tindar.usecases.editfilters.EditFiltersModel;

/**
 * controller for the Edit Filters UI
 */
public class EditFiltersController {
    /**
     * interactor of the edit filters feature
     */
    final EditFiltersInputBoundary userInput;

    /**
     * constructs Controller for the Edit Filters UI
     *
     * @param editFiltersUserInput interactor of the edit filters feature
     */
    public EditFiltersController(EditFiltersInputBoundary editFiltersUserInput) {
        this.userInput = editFiltersUserInput;
    }

    /**
     * gets filters information of the user
     *
     * @param userId the user id of the account
     * @return filters information of the user
     */
    public EditFiltersModel getFilters(String userId) {
        return userInput.getFilters(userId);
    }

    /**
     * updates filters information of the user
     *
     * @param userId the user id of the account
     * @param newFilters new filters information of the user to be updated
     * @throws InvalidAgeGroup if age group in the new filters argument is valid
     */
    public void updateFilters(String userId, EditFiltersModel newFilters) throws InvalidAgeGroup {
        userInput.updateFilters(userId, newFilters);
    }
}
