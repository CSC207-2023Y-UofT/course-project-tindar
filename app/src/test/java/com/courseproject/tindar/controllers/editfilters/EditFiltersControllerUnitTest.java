package com.courseproject.tindar.controllers.editfilters;

import static org.junit.Assert.assertEquals;

import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;
import com.courseproject.tindar.usecases.editfilters.EditFiltersDsResponseModel;
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

    EditFiltersDsResponseModel mockEditFiltersResponseModel =
            new EditFiltersDsResponseModel(PREFERRED_GENDERS, PREFERRED_LOCATIONS, PREFERRED_AGE_MINIMUM, PREFERRED_AGE_MAXIMUM);

    private class MockEditFiltersUserInput implements EditFiltersInputBoundary {
        @Override
        public EditFiltersDsResponseModel getFilters(String userId) {
            return mockEditFiltersResponseModel;
        }

        @Override
        public void updatePreferredGenders(String userId, ArrayList<String> preferredGenders) {
            assertEquals(USER_ID, userId);
            assertEquals(PREFERRED_GENDERS, preferredGenders);
        }

        @Override
        public void updatePreferredLocations(String userId, ArrayList<String> preferredLocations) {
            assertEquals(USER_ID, userId);
            assertEquals(PREFERRED_LOCATIONS, preferredLocations);
        }

        @Override
        public void updatePreferredAgeGroup(String userId, int minAge, int maxAge) {
            assertEquals(USER_ID, userId);
            assertEquals(PREFERRED_AGE_MINIMUM, minAge);
            assertEquals(PREFERRED_AGE_MAXIMUM, maxAge);
        }
    }

    EditFiltersInputBoundary mockEditFiltersUserInput = new MockEditFiltersUserInput();

    @Test
    public void getFilters() {
        EditFiltersController testEditFiltersController = new EditFiltersController(mockEditFiltersUserInput);
        EditFiltersDsResponseModel testEditFiltersResponseModel = testEditFiltersController.getFilters(USER_ID);
        assertEquals(PREFERRED_GENDERS, testEditFiltersResponseModel.getPreferredGenders());
        assertEquals(PREFERRED_LOCATIONS, testEditFiltersResponseModel.getPreferredLocations());
        assertEquals(PREFERRED_AGE_MINIMUM, testEditFiltersResponseModel.getPreferredAgeMinimum());
        assertEquals(PREFERRED_AGE_MAXIMUM, testEditFiltersResponseModel.getPreferredAgeMaximum());
    }

    @Test
    public void updatePreferredGenders() {
        EditFiltersController testEditFiltersController = new EditFiltersController(mockEditFiltersUserInput);
        testEditFiltersController.updatePreferredGenders(USER_ID, PREFERRED_GENDERS);
    }

    @Test
    public void updatePreferredLocations() {
        EditFiltersController testEditFiltersController = new EditFiltersController(mockEditFiltersUserInput);
        testEditFiltersController.updatePreferredLocations(USER_ID, PREFERRED_LOCATIONS);
    }

    @Test
    public void updatePreferredAgeGroup() throws InvalidAgeGroup {
        EditFiltersController testEditFiltersController = new EditFiltersController(mockEditFiltersUserInput);
        testEditFiltersController.updatePreferredAgeGroup(USER_ID, PREFERRED_AGE_MINIMUM, PREFERRED_AGE_MAXIMUM);
    }
}
