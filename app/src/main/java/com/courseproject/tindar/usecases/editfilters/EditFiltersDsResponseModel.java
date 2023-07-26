package com.courseproject.tindar.usecases.editfilters;

import java.util.ArrayList;

public class EditFiltersDsResponseModel {
    private final ArrayList<String> preferredGenders;
    private final ArrayList<String> preferredLocations;
    private final int preferredAgeMinimum;
    private final int preferredAgeMaximum;

    public EditFiltersDsResponseModel(ArrayList<String> preferredGenders, ArrayList<String> preferredLocations,
                                      int preferredAgeMinimum, int preferredAgeMaximum) {
        this.preferredGenders = preferredGenders;
        this.preferredLocations = preferredLocations;
        this.preferredAgeMinimum = preferredAgeMinimum;
        this.preferredAgeMaximum = preferredAgeMaximum;
    }

    public ArrayList<String> getPreferredGenders() {
        return preferredGenders;
    }

    public ArrayList<String> getPreferredLocations() {
        return preferredLocations;
    }

    public int getPreferredAgeMinimum() {
        return preferredAgeMinimum;
    }

    public int getPreferredAgeMaximum() {
        return preferredAgeMaximum;
    }
}
