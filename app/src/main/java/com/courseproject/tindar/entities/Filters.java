package com.courseproject.tindar.entities;

public class Filters {
    private final int preferredAgeMinimum;
    private final int preferredAgeMaximum;

    public Filters(int preferredAgeMinimum, int preferredAgeMaximum) {
        this.preferredAgeMinimum = preferredAgeMinimum;
        this.preferredAgeMaximum = preferredAgeMaximum;
    }

    /**
     * returns whether the preferred age group is valid. i.e. the minimum preferred age is greater than or equal to 19
     * and maximum preferred age is greater than or equal to minimum preferred age
     * @return true if the preferred age group is valid. false otherwise.
     */
    public boolean preferredAgeGroupIsValid() {
        if (preferredAgeMinimum < 19) {
            return false;
        }
        return preferredAgeMinimum <= preferredAgeMaximum;
    }
}
