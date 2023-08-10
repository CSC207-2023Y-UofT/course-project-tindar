package com.courseproject.tindar.usecases.editfilters;

import java.util.ArrayList;

import lombok.Getter;

public class EditFiltersModel {
    @Getter private final ArrayList<String> preferredGenders;
    @Getter private final ArrayList<String> preferredLocations;
    @Getter private final int preferredAgeMinimum;
    @Getter private final int preferredAgeMaximum;

    public EditFiltersModel(ArrayList<String> preferredGenders, ArrayList<String> preferredLocations,
                                      int preferredAgeMinimum, int preferredAgeMaximum) {
        this.preferredGenders = preferredGenders;
        this.preferredLocations = preferredLocations;
        this.preferredAgeMinimum = preferredAgeMinimum;
        this.preferredAgeMaximum = preferredAgeMaximum;
    }
}
