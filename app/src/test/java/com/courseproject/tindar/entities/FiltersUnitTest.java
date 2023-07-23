package com.courseproject.tindar.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FiltersUnitTest {
    @Test
    public void preferredAgeGroupIsValid() {
        Filters filters = new Filters(19, 99);
        assertTrue(filters.preferredAgeGroupIsValid());
    }

    @Test
    public void preferredAgeGroupIsValidMinimumLessThan19() {
        Filters filters = new Filters(18, 99);
        assertFalse(filters.preferredAgeGroupIsValid());
    }

    @Test
    public void preferredAgeGroupIsValidMinimumGreaterThanMaximum() {
        Filters filters = new Filters(99, 20);
        assertFalse(filters.preferredAgeGroupIsValid());
    }
}
