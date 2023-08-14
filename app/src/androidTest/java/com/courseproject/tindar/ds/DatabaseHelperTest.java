package com.courseproject.tindar.ds;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.courseproject.tindar.entities.MessageModel;
import com.courseproject.tindar.usecases.chat.ChatRequestModel;
import com.courseproject.tindar.usecases.conversationlist.ConversationDsResponseModel;
import com.courseproject.tindar.usecases.conversationlist.ConversationMessageDsResponseModel;
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

    @Test
    public void testAddAccount() {
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
                "fido", new GregorianCalendar(1997, 11, 27).getTime(),
                "Other", "Vancouver", "https://bbb", "Nice to meet you"
        );
        dbHelper.updateProfile(userId, newProfile);
        ViewProfileResponseModel updatedProfile = dbHelper.readProfile(userId);
        assertEquals("fido", updatedProfile.getDisplayName());
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
        ArrayList<MatchListDsResponseModel> displayNames = dbHelper.readUserIdAndDisplayNames(matchList);
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

    @Test
    public void testAddConversationAndFindConversationId() {
        dbHelper.addConversation(userId, otherUserId);
        String conversationId = dbHelper.findConversationId(userId, otherUserId);
        assertNotNull(conversationId);
    }

    @Test
    public void testAddConversationTwiceAndFindConversationId() {
        dbHelper.addConversation(userId, otherUserId);
        dbHelper.addConversation(userId, otherUserId);
        ArrayList<ConversationDsResponseModel> conversationList = dbHelper.readConversationList(userId);
        assertEquals(1, conversationList.size());
    }

    @Test
    public void testFindConversationIdWithNonExistingConversation() {
        String conversationId = dbHelper.findConversationId("some-user-11", "some-other-user-101");
        assertNull(conversationId);
    }

    @Test
    public void testReadConversationList() {
        dbHelper.addConversation(userId, otherUserId);
        dbHelper.addConversation(userId, thirdUserId);
        ArrayList<ConversationDsResponseModel> conversationList = dbHelper.readConversationList(userId);
        assertEquals(2, conversationList.size());
        assertEquals(userId, conversationList.get(0).getUserId1());
        assertEquals(otherUserId, conversationList.get(0).getUserId2());
        assertEquals(userId, conversationList.get(1).getUserId1());
        assertEquals(thirdUserId, conversationList.get(1).getUserId2());
    }

    @Test
    public void testReadConversationListWithNoConversation() {
        ArrayList<ConversationDsResponseModel> conversationList = dbHelper.readConversationList(userId);
        assertEquals(0, conversationList.size());
    }

    @Test
    public void testReadDisplayNamesForConversations() {
        // Test read display names returns list of user display names from database
        ArrayList<String> conversationList = new ArrayList<>();
        conversationList.add(userId);
        conversationList.add(otherUserId);
        ArrayList<String> displayNames = dbHelper.readDisplayNames(conversationList);
        assertEquals(2, displayNames.size());
        assertEquals("bell", displayNames.get(0));
        assertEquals("roger", displayNames.get(1));
    }

    @Test
    public void testReadDisplayNamesForConversationsWithEmptyInputList() {
        // Test read display names returns list of user display names from database
        ArrayList<String> conversationList = new ArrayList<>();
        ArrayList<String> displayNames = dbHelper.readDisplayNames(conversationList);
        assertEquals(0, displayNames.size());
    }

    @Test
    public void testAddMessageAndReadMessagesByConversationId() {
        ChatRequestModel message1 = new ChatRequestModel("first message sent",
                java.sql.Timestamp.valueOf("2005-04-06 09:01:10"), userId, otherUserId, "7");
        ChatRequestModel message2 = new ChatRequestModel("third message sent",
                java.sql.Timestamp.valueOf("2005-04-06 09:02:10"), otherUserId, userId, "7");
        ChatRequestModel message3 = new ChatRequestModel("second message sent",
                java.sql.Timestamp.valueOf("2005-04-06 09:01:17"), userId, otherUserId, "7");
        ChatRequestModel message4 = new ChatRequestModel("another message sent",
                java.sql.Timestamp.valueOf("2005-04-06 09:02:07"), userId, thirdUserId, "8");
        dbHelper.addMessage(message1);
        dbHelper.addMessage(message2);
        dbHelper.addMessage(message3);
        dbHelper.addMessage(message4);
        ArrayList<MessageModel> retrievedMessages = dbHelper.readMessagesByConversationId("7");
        assertEquals(3, retrievedMessages.size());
        assertEquals("first message sent", retrievedMessages.get(0).getMessageContent());
        assertEquals(java.sql.Timestamp.valueOf("2005-04-06 09:01:10"), retrievedMessages.get(0).getCreationTime());
        assertEquals(userId, retrievedMessages.get(0).getSentFromId());
        assertEquals(otherUserId, retrievedMessages.get(0).getSentToId());
        assertEquals("7", retrievedMessages.get(0).getConversationId());
        assertEquals("second message sent", retrievedMessages.get(1).getMessageContent());
        assertEquals("third message sent", retrievedMessages.get(2).getMessageContent());
        assertEquals(otherUserId, retrievedMessages.get(2).getSentFromId());
        assertEquals(userId, retrievedMessages.get(2).getSentToId());
    }

    @Test
    public void testReadMessagesByConversationIdWithNoMessage() {
        ArrayList<MessageModel> retrievedMessages = dbHelper.readMessagesByConversationId("7");
        assertEquals(0, retrievedMessages.size());
    }

    @Test
    public void testReadLastMessage() {
        ChatRequestModel message1 = new ChatRequestModel("first message sent",
                java.sql.Timestamp.valueOf("2005-04-06 09:01:10"), userId, otherUserId, "7");
        ChatRequestModel message2 = new ChatRequestModel("third message sent",
                java.sql.Timestamp.valueOf("2005-04-06 09:02:10"), otherUserId, userId, "7");
        ChatRequestModel message3 = new ChatRequestModel("second message sent",
                java.sql.Timestamp.valueOf("2005-04-06 09:01:17"), userId, otherUserId, "7");
        dbHelper.addMessage(message1);
        dbHelper.addMessage(message2);
        dbHelper.addMessage(message3);
        ConversationMessageDsResponseModel retrievedMessage = dbHelper.readLastMessage("7");
        assertEquals("third message sent", retrievedMessage.getLastMessage());
        assertEquals(java.sql.Timestamp.valueOf("2005-04-06 09:02:10"), retrievedMessage.getLastMessageTime());
    }

    @Test
    public void testReadLastMessageWithNoMessage() {
        ConversationMessageDsResponseModel retrievedMessage = dbHelper.readLastMessage("7");
        assertNull(retrievedMessage);
    }
}
