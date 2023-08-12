package com.courseproject.tindar.usecases.editfilters;

import com.courseproject.tindar.entities.Filters;
import com.courseproject.tindar.entities.FiltersFactory;
import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;

/**
 * interactor of the Edit Filters feature
 */
public class EditFiltersInteractor implements EditFiltersInputBoundary {

    /**
     * data-saving gateway of Edit Filters feature
     */
    final EditFiltersDsGateway editFiltersDsGateway;
    /**
     * presenter of the Edit Filters feature
     */
    final EditFiltersPresenter editFiltersPresenter;
    /**
     * factory to create Filters entity
     */
    final FiltersFactory filtersFactory;

    /**
     * contructs interactor of the Edit Filters feature
     *
     * @param editFiltersDsGateway gateway for the data-saving layer of Edit Filters feature
     * @param editFiltersPresenter presenter of the Edit Filters feature
     * @param filtersFactory factory to create Filters entity
     */
    public EditFiltersInteractor(EditFiltersDsGateway editFiltersDsGateway, EditFiltersPresenter editFiltersPresenter
            , FiltersFactory filtersFactory) {
        this.editFiltersDsGateway = editFiltersDsGateway;
        this.editFiltersPresenter = editFiltersPresenter;
        this.filtersFactory = filtersFactory;
    }

    /**
     * gets filters information of the user
     *
     * @param userId the user id of the account
     * @return filters information of the user
     */
    @Override
    public EditFiltersModel getFilters(String userId) {
        return editFiltersDsGateway.readFilters(userId);
    }

    /**
     * updates filters information of the user in the ds layer. This checks for if the preferred age group is valid
     * before updating. If the age group is invalid throws InvalidAgeGroup exception.
     *
     * @param userId the user id of the account the user id of the account
     * @param newFilters new filters information of the user to be updated
     * @throws InvalidAgeGroup if the age group provided is invalid
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
