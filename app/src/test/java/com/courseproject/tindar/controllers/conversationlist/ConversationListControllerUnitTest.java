package com.courseproject.tindar.controllers.conversationlist;

import static org.junit.Assert.assertEquals;

import com.courseproject.tindar.usecases.conversationlist.ConversationListInputBoundary;
import com.courseproject.tindar.usecases.conversationlist.ConversationResponseModel;

import org.junit.Test;

import java.util.ArrayList;

public class ConversationListControllerUnitTest {

    private static final String USER_ID_1 = "user1";
    private static final String CONVERSATION_PARTNER_ID = "user2";
    private static final String CONVERSATION_PARTNER_NAME = "jack";
    private static final String LAST_MESSAGE = "hi";
    private static final String LAST_MESSAGE_TIME = "21:00";

    public static class MockConversationListUserInput implements ConversationListInputBoundary {
        /**
         * Mock implementation of ConversationListInputBoundary for testing purposes
         **/


        @Override
        public ArrayList<ConversationResponseModel> getAllActiveConversations(String userId) {
            assertEquals(USER_ID_1, userId);

            ArrayList<ConversationResponseModel> conversations = new ArrayList<>();
            conversations.add(new ConversationResponseModel(CONVERSATION_PARTNER_ID, CONVERSATION_PARTNER_NAME, LAST_MESSAGE, LAST_MESSAGE_TIME));

            return conversations;
        }
    }

    @Test
    public void testGetDisplayNamesForMatches() {
        // Test that GetDisplayNamesForMatches is called
        ConversationListInputBoundary testConversationListUserInput = new ConversationListControllerUnitTest.MockConversationListUserInput();
        ConversationListController testConversationListController = new ConversationListController(testConversationListUserInput);
        ArrayList<ConversationResponseModel> returnedConversations = testConversationListController.getAllActiveConversations(USER_ID_1);
        assertEquals(1, returnedConversations.size());
        assertEquals(CONVERSATION_PARTNER_ID, returnedConversations.get(0).getConversationPartnerId());
        assertEquals(CONVERSATION_PARTNER_NAME, returnedConversations.get(0).getConversationPartnerName());
        assertEquals(LAST_MESSAGE, returnedConversations.get(0).getLastMessage());
        assertEquals(LAST_MESSAGE_TIME, returnedConversations.get(0).getLastMessageTime());
    }
}
