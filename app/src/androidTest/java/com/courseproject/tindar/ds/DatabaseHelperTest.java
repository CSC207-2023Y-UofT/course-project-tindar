package com.courseproject.tindar.ds;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.courseproject.tindar.usecases.editfilters.EditFiltersDsResponseModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsResponseModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;

@RunWith(AndroidJUnit4.class)
public class DatabaseHelperTest {

    private DatabaseHelper dbHelper;
    private String userId;

    @Before
    public void setUp() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dbHelper = DatabaseHelper.getInstance(appContext);
        userId = dbHelper.addAccount(true, "bell@exampleemail.com", "somepassword", "bell",
                "Bell", "Robin", new GregorianCalendar(2003, 9, 5).getTime(),
                "Female", "Calgary", "https://ccc", "I would like to",
                "Female, Male", "Calgary, Vancouver", 19, 999);
    }

    @After
    public void tearDown() {
        dbHelper.close();
    }

    // TODO: addAccount is indirectly tested in the Read methods. addAccount to be fully tested once there is
    //  DatabaseHelper method which reads the remaining columns of accounts table other than what ReadProfile method
    //  reads and returns

    @Test
    public void readProfile() {
        EditProfileDsResponseModel testProfile = dbHelper.readProfile(userId);
        assertEquals("bell", testProfile.getDisplayName());
        assertEquals(new GregorianCalendar(2003, 9, 5).getTime(), testProfile.getBirthdate());
        assertEquals("Female", testProfile.getGender());
        assertEquals("Calgary", testProfile.getLocation());
        assertEquals("https://ccc", testProfile.getProfilePictureLink());
        assertEquals("I would like to", testProfile.getAboutMe());
    }

    @Test
    public void updateBirthdate() {
        dbHelper.updateBirthdate(userId, new GregorianCalendar(1997, 11, 27).getTime());
        EditProfileDsResponseModel testProfile = dbHelper.readProfile(userId);
        assertEquals(new GregorianCalendar(1997, 11, 27).getTime(), testProfile.getBirthdate());
    }

    @Test
    public void updateGender() {
        dbHelper.updateGender(userId, "Other");
        EditProfileDsResponseModel testProfile = dbHelper.readProfile(userId);
        assertEquals("Other", testProfile.getGender());
    }

    @Test
    public void updateLocation() {
        dbHelper.updateLocation(userId, "Vancouver");
        EditProfileDsResponseModel testProfile = dbHelper.readProfile(userId);
        assertEquals("Vancouver", testProfile.getLocation());
    }

    @Test
    public void updateProfilePictureLink() {
        dbHelper.updateProfilePictureLink(userId, "https://bbb");
        EditProfileDsResponseModel testProfile = dbHelper.readProfile(userId);
        assertEquals("https://bbb", testProfile.getProfilePictureLink());
    }

    @Test
    public void updateAboutMe() {
        dbHelper.updateAboutMe(userId, "Nice to meet you");
        EditProfileDsResponseModel testProfile = dbHelper.readProfile(userId);
        assertEquals("Nice to meet you", testProfile.getAboutMe());
    }

    @Test
    public void readFilters() {
        EditFiltersDsResponseModel testFilters = dbHelper.readFilters(userId);
        assertEquals(new ArrayList<>(Arrays.asList("Female", "Male")), testFilters.getPreferredGenders()) ;
        assertEquals(new ArrayList<>(Arrays.asList("Calgary", "Vancouver")), testFilters.getPreferredLocations()) ;
        assertEquals(19, testFilters.getPreferredAgeMinimum());
        assertEquals(999, testFilters.getPreferredAgeMaximum());
    }

    @Test
    public void updatePreferredGenders() {
        dbHelper.updatePreferredGenders(userId, new ArrayList<>(Collections.singletonList("Female")));
        EditFiltersDsResponseModel testFilters = dbHelper.readFilters(userId);
        assertEquals(new ArrayList<>(Collections.singletonList("Female")), testFilters.getPreferredGenders()) ;
    }

    @Test
    public void updatePreferredGendersEmptyList() {
        dbHelper.updatePreferredGenders(userId, new ArrayList<>());
        EditFiltersDsResponseModel testFilters = dbHelper.readFilters(userId);
        assertEquals(new ArrayList<>(), testFilters.getPreferredGenders()) ;
    }

    @Test
    public void updatePreferredLocations() {
        dbHelper.updatePreferredGenders(userId, new ArrayList<>(Arrays.asList("Calgary", "Toronto")));
        EditFiltersDsResponseModel testFilters = dbHelper.readFilters(userId);
        assertEquals(new ArrayList<>(Arrays.asList("Calgary", "Toronto")), testFilters.getPreferredGenders());
    }

    @Test
    public void updatePreferredLocationsEmptyList() {
        dbHelper.updatePreferredLocations(userId, new ArrayList<>());
        EditFiltersDsResponseModel testFilters = dbHelper.readFilters(userId);
        assertEquals(new ArrayList<>(), testFilters.getPreferredLocations()) ;
    }

    @Test
    public void updatePreferredAgeGroup() {
        dbHelper.updatePreferredAgeGroup(userId, 21, 31);
        EditFiltersDsResponseModel testFilters = dbHelper.readFilters(userId);
        assertEquals(21, testFilters.getPreferredAgeMinimum()) ;
        assertEquals(31, testFilters.getPreferredAgeMaximum()) ;
    }
}
