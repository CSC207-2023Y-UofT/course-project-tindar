package com.courseproject.tindar.usecases.editfilters;

import java.util.ArrayList;

import lombok.Getter;

/**
 * data model for the filters for retrieving and updating filters information
 */
public class EditFiltersModel {
    /**
     * list of user's preferred genders for the potential match
     */
    @Getter private final ArrayList<String> preferredGenders;
    /**
     * list of user's preferred for the potential match
     */
    @Getter private final ArrayList<String> preferredLocations;
    /**
     * minimum age of user's preferred age group for the potential match
     */
    @Getter private final int preferredAgeMinimum;
    /**
     * maximum age of user's preferred age group for the potential match
     */
    @Getter private final int preferredAgeMaximum;

    /**
     * constructs data model for filters for retrieving and updating filters information
     *
     * @param preferredGenders list of user's preferred genders for the potential match
     * @param preferredLocations list of user's preferred locations for the potential match
     * @param preferredAgeMinimum minimum age of user's preferred age group for the potential match
     * @param preferredAgeMaximum maximum age of user's preferred age group for the potential match
     */
    public EditFiltersModel(ArrayList<String> preferredGenders, ArrayList<String> preferredLocations,
                                      int preferredAgeMinimum, int preferredAgeMaximum) {
        this.preferredGenders = preferredGenders;
        this.preferredLocations = preferredLocations;
        this.preferredAgeMinimum = preferredAgeMinimum;
        this.preferredAgeMaximum = preferredAgeMaximum;
    }
}
