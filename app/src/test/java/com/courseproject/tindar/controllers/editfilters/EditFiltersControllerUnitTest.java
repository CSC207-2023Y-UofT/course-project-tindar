package com.courseproject.tindar.controllers.editfilters;

import static org.junit.Assert.assertEquals;

import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;
import com.courseproject.tindar.usecases.editfilters.EditFiltersModel;
import com.courseproject.tindar.usecases.editfilters.EditFiltersInputBoundary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class EditFiltersControllerUnitTest {
    private static final String USER_ID = "99";
    private static final ArrayList<String> PREFERRED_GENDERS = new ArrayList<>(Arrays.asList("Female", "Other"));
    private static final ArrayList<String> PREFERRED_LOCATIONS = new ArrayList<>(Arrays.asList("Montreal", "Toronto"));
    private static final int PREFERRED_AGE_MINIMUM = 19;
    private static final int PREFERRED_AGE_MAXIMUM = 26;

    final EditFiltersModel mockEditFiltersResponseModel =
            new EditFiltersModel(PREFERRED_GENDERS, PREFERRED_LOCATIONS, PREFERRED_AGE_MINIMUM, PREFERRED_AGE_MAXIMUM);

    private class MockEditFiltersUserInput implements EditFiltersInputBoundary {
        @Override
        public EditFiltersModel getFilters(String userId) {
            return mockEditFiltersResponseModel;
        }

        @Override
        public void updateFilters(String userId, EditFiltersModel newFilters) {
            assertEquals(USER_ID, userId);
            assertEquals(PREFERRED_GENDERS, newFilters.getPreferredGenders());
            assertEquals(PREFERRED_LOCATIONS, newFilters.getPreferredLocations());
            assertEquals(PREFERRED_AGE_MINIMUM, newFilters.getPreferredAgeMinimum());
            assertEquals(PREFERRED_AGE_MAXIMUM, newFilters.getPreferredAgeMaximum());
        }
    }

    final EditFiltersInputBoundary mockEditFiltersUserInput = new MockEditFiltersUserInput();

    @Test
    public void testGetFilters() {
        EditFiltersController testEditFiltersController = new EditFiltersController(mockEditFiltersUserInput);
        EditFiltersModel testEditFiltersResponseModel = testEditFiltersController.getFilters(USER_ID);
        assertEquals(PREFERRED_GENDERS, testEditFiltersResponseModel.getPreferredGenders());
        assertEquals(PREFERRED_LOCATIONS, testEditFiltersResponseModel.getPreferredLocations());
        assertEquals(PREFERRED_AGE_MINIMUM, testEditFiltersResponseModel.getPreferredAgeMinimum());
        assertEquals(PREFERRED_AGE_MAXIMUM, testEditFiltersResponseModel.getPreferredAgeMaximum());
    }

    @Test
    public void testUpdateFilters() throws InvalidAgeGroup {
        EditFiltersController testEditFiltersController = new EditFiltersController(mockEditFiltersUserInput);
        EditFiltersModel newFilters = new EditFiltersModel(PREFERRED_GENDERS, PREFERRED_LOCATIONS,
                PREFERRED_AGE_MINIMUM, PREFERRED_AGE_MAXIMUM);
        testEditFiltersController.updateFilters(USER_ID, newFilters);
    }
}
