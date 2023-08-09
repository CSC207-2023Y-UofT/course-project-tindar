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

    EditFiltersModel mockEditFiltersResponseModel =
            new EditFiltersModel(PREFERRED_GENDERS, PREFERRED_LOCATIONS, PREFERRED_AGE_MINIMUM, PREFERRED_AGE_MAXIMUM);

    private class MockEditFiltersDsGateway implements EditFiltersDsGateway {
        @Override
        public EditFiltersModel readFilters(String userId) {
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

    EditFiltersDsGateway mockEditFiltersDsGateway = new MockEditFiltersDsGateway();
    EditFiltersPresenter editFiltersPresentationFormatter = new EditFiltersPresentationFormatter();
    FiltersFactory filtersFactory = new FiltersFactory();

    @Test
    public void testGetFilters() {
        EditFiltersInputBoundary testEditFiltersInteractor= new EditFiltersInteractor(mockEditFiltersDsGateway,
                editFiltersPresentationFormatter, filtersFactory);
        EditFiltersModel testEditFiltersResponseModel = testEditFiltersInteractor.getFilters(USER_ID);
        assertEquals(PREFERRED_GENDERS, testEditFiltersResponseModel.getPreferredGenders());
        assertEquals(PREFERRED_LOCATIONS, testEditFiltersResponseModel.getPreferredLocations());
        assertEquals(PREFERRED_AGE_MINIMUM, testEditFiltersResponseModel.getPreferredAgeMinimum());
        assertEquals(PREFERRED_AGE_MAXIMUM, testEditFiltersResponseModel.getPreferredAgeMaximum());
    }

    @Test
    public void testUpdateFilters() throws InvalidAgeGroup {
        EditFiltersInteractor testEditFiltersInteractor = new EditFiltersInteractor(mockEditFiltersDsGateway,
                editFiltersPresentationFormatter, filtersFactory);
        EditFiltersModel newFilters = new EditFiltersModel(PREFERRED_GENDERS, PREFERRED_LOCATIONS,
                PREFERRED_AGE_MINIMUM, PREFERRED_AGE_MAXIMUM);
        testEditFiltersInteractor.updateFilters(USER_ID, newFilters);
    }

    @Test
    public void testUpdateFiltersInvalidPreferredAgeGroup() {
        EditFiltersInteractor testEditFiltersInteractor = new EditFiltersInteractor(mockEditFiltersDsGateway,
                editFiltersPresentationFormatter, filtersFactory);
        EditFiltersModel newFiltersWithInvalidMinAge = new EditFiltersModel(PREFERRED_GENDERS, PREFERRED_LOCATIONS,
                PREFERRED_AGE_MINIMUM_INVALID, PREFERRED_AGE_MAXIMUM);
        Exception exception = assertThrows(InvalidAgeGroup.class,
                () -> testEditFiltersInteractor.updateFilters(USER_ID, newFiltersWithInvalidMinAge));
        assertEquals("Invalid age group. Minimum age should not be less than 19 or greater than maximum age.", exception.getMessage());
    }
}
