package com.courseproject.tindar.usecases.editfilters;

import java.util.ArrayList;

public interface EditFiltersDsGateway {
    EditFiltersDsResponseModel readFilters(String userId);

    void updatePreferredGenders(String userId, ArrayList<String> preferredGenders);

    void updatePreferredLocations(String userId, ArrayList<String> preferredLocations);

    void updatePreferredAgeGroup(String userId, int minAge, int maxAge);
}
