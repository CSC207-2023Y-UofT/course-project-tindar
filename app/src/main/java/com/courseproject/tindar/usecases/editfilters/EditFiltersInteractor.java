package com.courseproject.tindar.usecases.editfilters;

import com.courseproject.tindar.entities.Filters;
import com.courseproject.tindar.entities.FiltersFactory;
import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;

import java.util.ArrayList;

public class EditFiltersInteractor implements EditFiltersInputBoundary {

    final EditFiltersDsGateway editFiltersDsGateway;
    final EditFiltersPresenter editFiltersPresenter;
    final FiltersFactory filtersFactory;

    public EditFiltersInteractor(EditFiltersDsGateway editFiltersDsGateway, EditFiltersPresenter editFiltersPresenter
            , FiltersFactory filtersFactory) {
        this.editFiltersDsGateway = editFiltersDsGateway;
        this.editFiltersPresenter = editFiltersPresenter;
        this.filtersFactory = filtersFactory;
    }

    @Override
    public EditFiltersDsResponseModel getFilters(String userId) {
        return editFiltersDsGateway.readFilters(userId);
    }

    @Override
    public void updatePreferredGenders(String userId, ArrayList<String> preferredGenders) {
        editFiltersDsGateway.updatePreferredGenders(userId, preferredGenders);
    }

    @Override
    public void updatePreferredLocations(String userId, ArrayList<String> preferredLocations) {
        editFiltersDsGateway.updatePreferredLocations(userId, preferredLocations);
    }

    /**
     * updates preferred age group to the ds layer if the age group is valid. If the age group is invalid throws
     * InvalidAgeGroup exception.
     * @param userId    user's id
     * @param minAge    minimum preferred age
     * @param maxAge    maximum preferred age
     * @throws InvalidAgeGroup  if the age group is invalid
     */
    @Override
    public void updatePreferredAgeGroup(String userId, int minAge, int maxAge) throws InvalidAgeGroup {
        Filters filters = filtersFactory.create(minAge, maxAge);
        if (!filters.preferredAgeGroupIsValid()) {
            editFiltersPresenter.prepareFailView(
                    "Invalid age group. Minimum age should not be less than 19 or greater than maximum age.");
        }
        editFiltersDsGateway.updatePreferredAgeGroup(userId, minAge, maxAge);
    }
}
