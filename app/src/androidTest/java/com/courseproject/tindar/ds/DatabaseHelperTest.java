package com.courseproject.tindar.ds;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.courseproject.tindar.usecases.editprofile.EditProfileResponseModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
            "Female", "Calgary", "https://ccc", "I would like to");
    }

    @After
    public void tearDown() {
        dbHelper.close();
    }

    // TODO: addAccount is indirectly tested in the Read methods. addAccount to be fully tested once there is
    //  DatabaseHelper method which reads the remaining columns of accounts table other than what ReadProfile method
    //  reads and returns

    @Test
    public void ReadProfile() {
        EditProfileResponseModel testProfile = dbHelper.readProfile(userId);
        assertEquals("Female", testProfile.getBirthdate(), new GregorianCalendar(2003, 9, 5).getTime()) ;
        assertEquals("Female", testProfile.getGender()) ;
        assertEquals("Calgary", testProfile.getLocation());
        assertEquals( "https://ccc", testProfile.getProfilePictureLink());
        assertEquals("I would like to", testProfile.getAboutMe());
    }

    @Test
    public void updateBirthdate() {
        dbHelper.updateBirthdate(userId, new GregorianCalendar(1997, 11, 27).getTime());
        EditProfileResponseModel testProfile = dbHelper.readProfile(userId);
        assertEquals(new GregorianCalendar(1997, 11, 27).getTime(), testProfile.getBirthdate());
    }

    @Test
    public void updateGender() {
        dbHelper.updateGender(userId, "Other");
        EditProfileResponseModel testProfile = dbHelper.readProfile(userId);
        assertEquals("Other", testProfile.getGender());
    }

    @Test
    public void updateLocation() {
        dbHelper.updateLocation(userId, "Vancouver");
        EditProfileResponseModel testProfile = dbHelper.readProfile(userId);
        assertEquals("Vancouver", testProfile.getLocation());
    }

    @Test
    public void updateProfilePictureLink() {
        dbHelper.updateProfilePictureLink(userId, "https://bbb");
        EditProfileResponseModel testProfile = dbHelper.readProfile(userId);
        assertEquals("https://bbb", testProfile.getProfilePictureLink());
    }

    @Test
    public void updateAboutMe() {
        dbHelper.updateAboutMe(userId, "Nice to meet you");
        EditProfileResponseModel testProfile = dbHelper.readProfile(userId);
        assertEquals("Nice to meet you", testProfile.getAboutMe());
    }
}
