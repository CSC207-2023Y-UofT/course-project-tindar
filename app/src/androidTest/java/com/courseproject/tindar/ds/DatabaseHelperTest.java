package com.courseproject.tindar.ds;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.courseproject.tindar.usecases.editfilters.EditFiltersDsResponseModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsResponseModel;
import com.courseproject.tindar.usecases.likelist.LikeListDsResponseModel;

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
    private String otherUserId;
    private String thirdUserId;

    @Before
    public void setUp() {
        // Fake users for testing purposes
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dbHelper = DatabaseHelper.getInstance(appContext);
        userId = dbHelper.addAccount(true, "bell@exampleemail.com", "somepassword", "bell",
                "Bell", "Robin", new GregorianCalendar(2003, 9, 5).getTime(),
                "Female", "Calgary", "https://ccc", "I would like to",
                "Female, Male", "Calgary, Vancouver", 19, 999);
        otherUserId = dbHelper.addAccount(true, "rogers@exampleemail.com", "someotherpassword", "roger",
                "roger", "fido", new GregorianCalendar(2003, 12, 3).getTime(),
                "Female", "Calgary", "https://ccc", "I would like to",
                "Female, Male", "Calgary, Vancouver", 19, 999);
        thirdUserId = dbHelper.addAccount(true, "telus@exampleemail.com", "somethirdpassword", "ted",
                "ted", "telus", new GregorianCalendar(2001, 12, 3).getTime(),
                "Male", "Toronto", "https://ccc", "I would like to",
                "Female, Male", "Calgary, Vancouver", 19, 999);
        dbHelper.addLike(otherUserId, userId);
    }

    @After
    public void tearDown() {
        dbHelper.deleteUserId();
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

    @Test
    public void readUserId(){
        String userIdRead = dbHelper.readUserId("bell@exampleemail.com", "somepassword");
        assertEquals(userId, userIdRead);
    }

    @Test
    public void readUserIdWhenPasswordWrong(){
        String userIdRead = dbHelper.readUserId("bell@exampleemail.com", "somassword");
        assertNull(userIdRead);
    }

    @Test
    public void readUserIdWhenEmailWrong(){
        String userIdRead = dbHelper.readUserId("bel@exampleemail.com", "somepassword");
        assertNull(userIdRead);
    }
    @Test
    public void testAddLikeAndCheckLiked(){
        // Test userId "likes" otherUseId, and are added to likeList
        dbHelper.addLike(userId, otherUserId);
        assertTrue(dbHelper.checkLiked(userId, otherUserId));
    }

    @Test
    public void testRemoveLikeAndCheckLikedWhenRecordExist() {
        // Test userID "unlikes" otherUserId and is removed from like list when previously there
        assertTrue(dbHelper.checkLiked(otherUserId, userId));
        dbHelper.removeLike(otherUserId, userId);
        assertFalse(dbHelper.checkLiked(otherUserId, userId));
    }

    @Test
    public void testRemoveLikeAndCheckLikedWhenRecordNotExist() {
        // Test userID "unlikes" otherUserId and is removed from like list when not previously there
        assertFalse(dbHelper.checkLiked(userId, otherUserId));
        dbHelper.removeLike(userId, otherUserId);
        assertFalse(dbHelper.checkLiked(userId, otherUserId));
    }

    @Test
    public void testTwoAddLikeDoesNotProduceDuplicateRecord() {
        // Test that userId "liking" otherUserId twice does not add two of the same Id into likeList
        dbHelper.addLike(userId, otherUserId);
        assertTrue(dbHelper.checkLiked(userId, otherUserId));
        dbHelper.addLike(userId, otherUserId);
        assertTrue(dbHelper.checkLiked(userId, otherUserId));
        dbHelper.removeLike(userId, otherUserId);
        assertFalse(dbHelper.checkLiked(userId, otherUserId));
    }

    @Test
    public void testAddToMatchedAndReadMatchList() {
        // Test two users are matched and added to the match list in database
        dbHelper.addToMatched(userId, otherUserId);
        ArrayList<String[]> matchList = dbHelper.readMatchList(userId);
        assertArrayEquals(new String[]{userId, otherUserId}, matchList.get(0));
        assertEquals(1, matchList.size());
        ArrayList<String[]> otherMatchList = dbHelper.readMatchList(otherUserId);
        assertArrayEquals(new String[]{userId, otherUserId}, otherMatchList.get(0));
        assertEquals(1, otherMatchList.size());
    }

    @Test
    public void testRemoveFromMatchedWhenRecordExist() {
        // Test the matched users are removed from match list then removeFromMatched is called
        // and both users are recorded in match list
        dbHelper.addToMatched(userId, otherUserId);
        ArrayList<String[]> matchList = dbHelper.readMatchList(userId);
        assertArrayEquals(new String[]{userId, otherUserId}, matchList.get(0));
        assertEquals(1, matchList.size());
        dbHelper.removeFromMatched(userId, otherUserId);
        ArrayList<String[]> matchListAfterRemove = dbHelper.readMatchList(userId);
        assertTrue(matchListAfterRemove.isEmpty());
    }

    @Test
    public void testRemoveFromMatchedWhenRecordNotExist() {
        // Test the matched users are removed from match list then removeFromMatched is called
        // and both users are not recorded in match list
        ArrayList<String[]> matchList = dbHelper.readMatchList(userId);
        assertTrue(matchList.isEmpty());
        dbHelper.removeFromMatched(userId, otherUserId);
        ArrayList<String[]> matchListAfterRemove = dbHelper.readMatchList(userId);
        assertTrue(matchListAfterRemove.isEmpty());
    }

    @Test
    public void testTwoAddToMatchedDoesNotProduceDuplicate() {
        // Test adding two users to match list does not produce duplicate in list
        dbHelper.addToMatched(userId, otherUserId);
        ArrayList<String[]> matchList = dbHelper.readMatchList(userId);
        assertArrayEquals(new String[]{userId, otherUserId}, matchList.get(0));
        assertEquals(1, matchList.size());
        dbHelper.addToMatched(userId, otherUserId);
        ArrayList<String[]> secondMatchList = dbHelper.readMatchList(userId);
        assertArrayEquals(new String[]{userId, otherUserId}, secondMatchList.get(0));
        assertEquals(1, secondMatchList.size());
    }

    @Test
    public void testReadDisplayNames() {
        // Test read display names returns list of user display names from database
        ArrayList<String> matchList = new ArrayList<>();
        matchList.add(userId);
        matchList.add(thirdUserId);
        ArrayList<LikeListDsResponseModel> displayNames = dbHelper.readDisplayNames(matchList);
        assertEquals(displayNames.get(0).getUserId(), userId);
        assertEquals(displayNames.get(0).getDisplayName(), "bell");
        assertEquals(displayNames.get(1).getUserId(), thirdUserId);
        assertEquals(displayNames.get(1).getDisplayName(), "ted");
    }
}
