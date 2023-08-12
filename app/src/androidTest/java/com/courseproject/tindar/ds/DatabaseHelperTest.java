package com.courseproject.tindar.ds;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.courseproject.tindar.usecases.editaccount.EditAccountDsResponseModel;
import com.courseproject.tindar.usecases.editfilters.EditFiltersModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileRequestModel;
import com.courseproject.tindar.usecases.matchlist.MatchListDsResponseModel;
import com.courseproject.tindar.usecases.signup.SignUpDsRequestModel;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileResponseModel;

import org.junit.*;
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
        dbHelper = DatabaseHelper.getTestInstance(appContext);

        dbHelper.deleteAllDbRecords();

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
        dbHelper.close();
    }

    // TODO: addAccount is indirectly tested in the Read methods. addAccount to be fully tested once there is
    //  DatabaseHelper method which reads the remaining columns of accounts table other than what ReadProfile method
    //  reads and returns

    @Test
    public void testAddAccount() {
        //TODO: get readAccount to check if the value inserted is right
        SignUpDsRequestModel accountCredentials = new SignUpDsRequestModel("april", "april@someemail.com",
                "aprilpassword");
        String createdUserId = dbHelper.addAccount(accountCredentials);
        ViewProfileResponseModel profile = dbHelper.readProfile(createdUserId);
        assertEquals("april", profile.getDisplayName());
    }

    @Test
    public void testCheckIfEmailAlreadyUsed() {
        assertTrue(dbHelper.checkIfEmailAlreadyUsed("bell@exampleemail.com"));
    }

    @Test
    public void testReadProfile() {
        ViewProfileResponseModel testProfile = dbHelper.readProfile(userId);
        assertEquals("bell", testProfile.getDisplayName());
        assertEquals(new GregorianCalendar(2003, 9, 5).getTime(), testProfile.getBirthdate());
        assertEquals("Female", testProfile.getGender());
        assertEquals("Calgary", testProfile.getLocation());
        assertEquals("https://ccc", testProfile.getProfilePictureLink());
        assertEquals("I would like to", testProfile.getAboutMe());
    }

    @Test
    public void testUpdateProfile() {
        EditProfileRequestModel newProfile = new EditProfileRequestModel(
                new GregorianCalendar(1997, 11, 27).getTime(),
                "Other", "Vancouver", "https://bbb", "Nice to meet you"
        );
        dbHelper.updateProfile(userId, newProfile);
        ViewProfileResponseModel updatedProfile = dbHelper.readProfile(userId);
        assertEquals(new GregorianCalendar(1997, 11, 27).getTime(), updatedProfile.getBirthdate());
        assertEquals("Other", updatedProfile.getGender());
        assertEquals("Vancouver", updatedProfile.getLocation());
        assertEquals("https://bbb", updatedProfile.getProfilePictureLink());
        assertEquals("Nice to meet you", updatedProfile.getAboutMe());
    }

    @Test
    public void testReadFilters() {
        EditFiltersModel testFilters = dbHelper.readFilters(userId);
        assertEquals(new ArrayList<>(Arrays.asList("Female", "Male")), testFilters.getPreferredGenders()) ;
        assertEquals(new ArrayList<>(Arrays.asList("Calgary", "Vancouver")), testFilters.getPreferredLocations()) ;
        assertEquals(19, testFilters.getPreferredAgeMinimum());
        assertEquals(999, testFilters.getPreferredAgeMaximum());
    }

    @Test
    public void testUpdateFilters() {
        EditFiltersModel newFilters = new EditFiltersModel(
                new ArrayList<>(Collections.singletonList("Female")),
                new ArrayList<>(Arrays.asList("Calgary", "Toronto")),
                21, 31
        );
        dbHelper.updateFilters(userId, newFilters);
        EditFiltersModel updatedFilters = dbHelper.readFilters(userId);
        assertEquals(new ArrayList<>(Collections.singletonList("Female")), updatedFilters.getPreferredGenders());
        assertEquals(new ArrayList<>(Arrays.asList("Calgary", "Toronto")), updatedFilters.getPreferredLocations());
        assertEquals(21, updatedFilters.getPreferredAgeMinimum());
        assertEquals(31, updatedFilters.getPreferredAgeMaximum());
    }

    @Test
    public void testUpdateFiltersEmptyList() {
        EditFiltersModel newFilters = new EditFiltersModel(
                new ArrayList<>(), new ArrayList<>(), 23, 25);
        dbHelper.updateFilters(userId, newFilters);
        EditFiltersModel updatedFilters = dbHelper.readFilters(userId);
        assertTrue(updatedFilters.getPreferredGenders().isEmpty());
        assertTrue(updatedFilters.getPreferredLocations().isEmpty());
        assertEquals(23, updatedFilters.getPreferredAgeMinimum());
        assertEquals(25, updatedFilters.getPreferredAgeMaximum());
    }

    @Test
    public void testReadUserId(){
        String userIdRead = dbHelper.readUserId("bell@exampleemail.com", "somepassword");
        assertEquals(userId, userIdRead);
    }

    @Test
    public void testReadUserIdWhenPasswordWrong(){
        String userIdRead = dbHelper.readUserId("bell@exampleemail.com", "somassword");
        assertNull(userIdRead);
    }

    @Test
    public void testReadUserIdWhenEmailWrong(){
        String userIdRead = dbHelper.readUserId("bel@exampleemail.com", "somepassword");
        assertNull(userIdRead);
    }
    @Test
    public void testAddLikeAndCheckLiked(){
        // Test userId "likes" otherUseId, and are added to likeList
        // addLike is tested since @Setup
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
        ArrayList<MatchListDsResponseModel> displayNames = dbHelper.readDisplayNames(matchList);
        assertEquals(displayNames.get(0).getUserId(), userId);
        assertEquals(displayNames.get(0).getDisplayName(), "bell");
        assertEquals(displayNames.get(1).getUserId(), thirdUserId);
        assertEquals(displayNames.get(1).getDisplayName(), "ted");
    }

    @Test
    public void testReadAccount() {
        EditAccountDsResponseModel testAccount = dbHelper.readAccount(userId);
        assertTrue(testAccount.getIsActiveStatus());
        assertEquals("bell@exampleemail.com", testAccount.getEmail());
        assertEquals("somepassword", testAccount.getPassword());
    }

    @Test
    public void testUpdateIsAccountStatus() {
        dbHelper.updateIsActiveStatus(userId, false);
        EditAccountDsResponseModel testAccount = dbHelper.readAccount(userId);
        assertFalse(testAccount.getIsActiveStatus());
    }

    @Test
    public void testUpdateEmail() {
        dbHelper.updateEmail(userId, "something@email.com");
        EditAccountDsResponseModel testAccount = dbHelper.readAccount(userId);
        assertEquals(testAccount.getEmail(), "something@email.com");
    }

    @Test
    public void testUpdateEmailNotUnique() {
        dbHelper.updateEmail(userId, "rogers@exampleemail.com");
        EditAccountDsResponseModel testAccount = dbHelper.readAccount(userId);
        boolean test = false;
        try {
            assertEquals(testAccount.getEmail(), "rogers@exampleemail.com");
        }
        catch (AssertionError e) {
            test = true;
        }
        assertTrue(test);
    }

    @Test
    public void testUpdatePassword() {
        dbHelper.updatePassword(userId, "newpassword");
        EditAccountDsResponseModel testAccount = dbHelper.readAccount(userId);
        assertEquals(testAccount.getPassword(), "newpassword");
    }

    @Test
    public void testGetAllUserIds(){
        ArrayList<String> userList = new ArrayList<>();
        userList.add(otherUserId);
        userList.add(thirdUserId);
        ArrayList<String> dbUserList = dbHelper.getAllOtherUserIds(userId);

        assertEquals(userList, dbUserList);
    }
}
