package com.courseproject.tindar.usecases.editfilters;

public interface EditFiltersDsGateway {
    EditFiltersModel readFilters(String userId);
    void updateFilters(String userId, EditFiltersModel newFilters);
}
