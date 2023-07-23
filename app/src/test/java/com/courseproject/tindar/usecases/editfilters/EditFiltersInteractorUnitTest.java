package com.courseproject.tindar.usecases.editfilters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.courseproject.tindar.entities.FiltersFactory;
import com.courseproject.tindar.presenters.editfilters.EditFiltersPresentationFormatter;
import com.courseproject.tindar.presenters.editfilters.InvalidAgeGroup;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class EditFiltersInteractorUnitTest {
    private static final String USER_ID = "99";
    private static final ArrayList<String> PREFERRED_GENDERS = new ArrayList<>(Arrays.asList("Female", "Other"));
    private static final ArrayList<String> PREFERRED_LOCATIONS = new ArrayList<>(Arrays.asList("Montreal", "Toronto"));
    private static final int PREFERRED_AGE_MINIMUM = 19;
    private static final int PREFERRED_AGE_MINIMUM_INVALID = 18;
    private static final int PREFERRED_AGE_MAXIMUM = 26;

    EditFiltersDsResponseModel mockEditFiltersResponseModel =
            new EditFiltersDsResponseModel(PREFERRED_GENDERS, PREFERRED_LOCATIONS, PREFERRED_AGE_MINIMUM, PREFERRED_AGE_MAXIMUM);

    private class MockEditFiltersDsGateway implements EditFiltersDsGateway {
        @Override
        public EditFiltersDsResponseModel readFilters(String userId) {
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

    EditFiltersDsGateway mockEditFiltersDsGateway = new MockEditFiltersDsGateway();
    EditFiltersPresenter editFiltersPresentationFormatter = new EditFiltersPresentationFormatter();
    FiltersFactory filtersFactory = new FiltersFactory();

    @Test
    public void getFilters() {
        EditFiltersInputBoundary testEditFiltersInteractor= new EditFiltersInteractor(mockEditFiltersDsGateway,
                editFiltersPresentationFormatter, filtersFactory);
        EditFiltersDsResponseModel testEditFiltersResponseModel = testEditFiltersInteractor.getFilters(USER_ID);
        assertEquals(PREFERRED_GENDERS, testEditFiltersResponseModel.getPreferredGenders());
        assertEquals(PREFERRED_LOCATIONS, testEditFiltersResponseModel.getPreferredLocations());
        assertEquals(PREFERRED_AGE_MINIMUM, testEditFiltersResponseModel.getPreferredAgeMinimum());
        assertEquals(PREFERRED_AGE_MAXIMUM, testEditFiltersResponseModel.getPreferredAgeMaximum());
    }

    @Test
    public void updatePreferredGenders() {
        EditFiltersInteractor testEditFiltersInteractor = new EditFiltersInteractor(mockEditFiltersDsGateway,
                editFiltersPresentationFormatter, filtersFactory);
        testEditFiltersInteractor.updatePreferredGenders(USER_ID, PREFERRED_GENDERS);
    }

    @Test
    public void updatePreferredLocations() {
        EditFiltersInteractor testEditFiltersInteractor = new EditFiltersInteractor(mockEditFiltersDsGateway,
                editFiltersPresentationFormatter, filtersFactory);
        testEditFiltersInteractor.updatePreferredLocations(USER_ID, PREFERRED_LOCATIONS);
    }

    @Test
    public void updatePreferredAgeGroup() throws InvalidAgeGroup {
        EditFiltersInteractor testEditFiltersInteractor = new EditFiltersInteractor(mockEditFiltersDsGateway,
                editFiltersPresentationFormatter, filtersFactory);
        testEditFiltersInteractor.updatePreferredAgeGroup(USER_ID, PREFERRED_AGE_MINIMUM, PREFERRED_AGE_MAXIMUM);
    }

    @Test
    public void updatePreferredAgeGroupInvalidAgeGroup() {
        EditFiltersInteractor testEditFiltersInteractor = new EditFiltersInteractor(mockEditFiltersDsGateway,
                editFiltersPresentationFormatter, filtersFactory);
        Exception exception = assertThrows(InvalidAgeGroup.class, () -> testEditFiltersInteractor.updatePreferredAgeGroup(USER_ID, PREFERRED_AGE_MINIMUM_INVALID,
                PREFERRED_AGE_MAXIMUM));
        assertEquals("Invalid age group. Minimum age should not be less than 19 or greater than maximum age.", exception.getMessage());
    }
}
