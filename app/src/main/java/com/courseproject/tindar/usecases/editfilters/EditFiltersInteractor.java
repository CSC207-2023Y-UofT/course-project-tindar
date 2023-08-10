package com.courseproject.tindar.usecases.editfilters;

import com.courseproject.tindar.entities.Filters;
import com.courseproject.tindar.entities.FiltersFactory;
import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;

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
    public EditFiltersModel getFilters(String userId) {
        return editFiltersDsGateway.readFilters(userId);
    }

    /**
     * updates filters to the ds layer. This checks for if the preferred age group is valid before updating.
     * If the age group is invalid throws InvalidAgeGroup exception.
     *
     * @param userId    user's id
     * @param newFilters    new Filters to be updated
     * @throws InvalidAgeGroup  if the age group is invalid
     */
    @Override
    public void updateFilters(String userId, EditFiltersModel newFilters) throws InvalidAgeGroup {
        Filters filters = filtersFactory.create(newFilters.getPreferredAgeMinimum(), newFilters.getPreferredAgeMaximum());
        if (!filters.preferredAgeGroupIsValid()) {
            editFiltersPresenter.prepareFailView(
                    "Invalid age group. Minimum age should not be less than 19 or greater than maximum age.");
        }
        editFiltersDsGateway.updateFilters(userId, newFilters);
    }
}
