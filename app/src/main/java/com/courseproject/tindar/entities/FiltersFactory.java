package com.courseproject.tindar.entities;

/**
 * factory class to create Filters entity
 */
public class FiltersFactory {
    /**
     * creates Filters entity
     *
     * @param preferredAgeMinimum minimum age of user's preferred age group
     * @param preferredAgeMaximum maximum age of user's preferred age group
     * @return Filters entity
     */
    public Filters create(int preferredAgeMinimum, int preferredAgeMaximum) {
        return new Filters(preferredAgeMinimum, preferredAgeMaximum);
    }
}
