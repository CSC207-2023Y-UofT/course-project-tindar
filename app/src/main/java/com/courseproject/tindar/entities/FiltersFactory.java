package com.courseproject.tindar.entities;

public class FiltersFactory {
    public Filters create(int preferredAgeMinimum, int preferredAgeMaximum) {
        return new Filters(preferredAgeMinimum, preferredAgeMaximum);
    }
}
