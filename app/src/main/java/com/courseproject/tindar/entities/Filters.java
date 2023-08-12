package com.courseproject.tindar.entities;

/**
 * (a user's) filters entity
 */
public class Filters {
    /**
     * minimum age of user's preferred age group for the potential match
     */
    private final int preferredAgeMinimum;
    /**
     * maximum age of user's preferred age group for the potential match
     */
    private final int preferredAgeMaximum;

    /**
     * constructs filters entity
     *
     * @param preferredAgeMinimum minimum age of user's preferred age group for the potential match
     * @param preferredAgeMaximum maximum age of user's preferred age group for the potential match
     */
    public Filters(int preferredAgeMinimum, int preferredAgeMaximum) {
        this.preferredAgeMinimum = preferredAgeMinimum;
        this.preferredAgeMaximum = preferredAgeMaximum;
    }

    /**
     * returns whether the preferred age group is valid. i.e. the minimum preferred age is greater than or equal to 19
     * and maximum preferred age is greater than or equal to minimum preferred age
     *
     * @return true if the preferred age group is valid. false otherwise.
     */
    public boolean preferredAgeGroupIsValid() {
        if (preferredAgeMinimum < 19) {
            return false;
        }
        return preferredAgeMinimum <= preferredAgeMaximum;
    }
}
