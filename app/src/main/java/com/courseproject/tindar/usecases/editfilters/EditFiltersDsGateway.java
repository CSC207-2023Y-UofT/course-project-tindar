package com.courseproject.tindar.usecases.editfilters;

/**
 * interface for the data-saving gateway of Edit Filters feature
 */
public interface EditFiltersDsGateway {
    /**
     * retrieves filters information of the user
     *
     * @param userId the user id of the account
     * @return filters information of the user
     */
    EditFiltersModel readFilters(String userId);

    /**
     * updates filters information of the user
     *
     * @param userId the user id of the account
     * @param newFilters new filters information of the user to be updated
     */
    void updateFilters(String userId, EditFiltersModel newFilters);
}
