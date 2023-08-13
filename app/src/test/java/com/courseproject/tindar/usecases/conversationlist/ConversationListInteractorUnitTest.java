package com.courseproject.tindar.usecases.conversationlist;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ConversationListInteractorUnitTest {


    private static final String CONVERSATION_ID = "7";
    private static final String USER_ID_1 = "1";
    private static final String USER_ID_2 = "2";
    private static final String USER_ID_3 = "3";
    private static final String LAST_MESSAGE_1 = "hi";
    private static final Timestamp LAST_MESSAGE_TIME_1 = java.sql.Timestamp.valueOf("2005-04-06 09:02:10");
    private static final String LAST_MESSAGE_2 = "hello";
    private static final Timestamp LAST_MESSAGE_TIME_2 = java.sql.Timestamp.valueOf("2005-04-07 09:02:10");

    private static final String USER_DISPLAY_NAME_1 = "rogers";
    private static final String USER_DISPLAY_NAME_2 = "bell";

    private static class MockConversationListDsGateway implements ConversationListDsGateway {
        /** Mock implementation of ConversationListDsGateway for testing purposes **/

        String userId1;
        String userId2;
        ConversationMessageDsResponseModel lastMessage;
        String displayName;

        public MockConversationListDsGateway(String userId1, String userId2, ConversationMessageDsResponseModel lastMessage, String displayName) {
            this.userId1 = userId1;
            this.userId2 = userId2;
            this.lastMessage = lastMessage;
            this.displayName = displayName;
        }

        @Override
        public ArrayList<ConversationDsResponseModel> readConversationList(String userId) {
            ArrayList<ConversationDsResponseModel> conversationList = new ArrayList<>();
            ConversationDsResponseModel conversationFromDs = new ConversationDsResponseModel(CONVERSATION_ID, userId1, userId2);
            conversationList.add(conversationFromDs);
            return conversationList;
        }

        @Override
        public ConversationMessageDsResponseModel readLastMessage(String conversationId) {
            return lastMessage;
        }

        @Override
        public ArrayList<String> readDisplayNames(ArrayList<String> userIds) {
            ArrayList<String> displayNames = new ArrayList<>();
            displayNames.add(displayName);
            return displayNames;
        }
    }

    @Test
    public void testGetAllActiveConversations() {
        ConversationMessageDsResponseModel lastMessage = new ConversationMessageDsResponseModel(LAST_MESSAGE_2, LAST_MESSAGE_TIME_2);
        ConversationListDsGateway conversationListDsGateway = new MockConversationListDsGateway(USER_ID_1, USER_ID_2, lastMessage, USER_DISPLAY_NAME_2);
        ConversationListInteractor conversationListInteractor = new ConversationListInteractor(conversationListDsGateway);
        ArrayList<ConversationResponseModel> returnedConversations = conversationListInteractor.getAllActiveConversations(USER_ID_1);
        assertEquals(1, returnedConversations.size());
        assertEquals(USER_ID_2, returnedConversations.get(0).getConversationPartnerId());
        assertEquals(USER_DISPLAY_NAME_2, returnedConversations.get(0).getConversationPartnerName());
        assertEquals(LAST_MESSAGE_2, returnedConversations.get(0).getLastMessage());
        assertEquals(LAST_MESSAGE_TIME_2.toString(), returnedConversations.get(0).getLastMessageTime());
    }

    @Test
    public void testGetAllActiveConversationsReversed() {
        ConversationMessageDsResponseModel lastMessage = new ConversationMessageDsResponseModel(LAST_MESSAGE_1, LAST_MESSAGE_TIME_1);
        ConversationListDsGateway conversationListDsGateway = new MockConversationListDsGateway(USER_ID_1, USER_ID_3, lastMessage, USER_DISPLAY_NAME_1);
        ConversationListInteractor conversationListInteractor = new ConversationListInteractor(conversationListDsGateway);
        ArrayList<ConversationResponseModel> returnedConversations = conversationListInteractor.getAllActiveConversations(USER_ID_3);
        assertEquals(1, returnedConversations.size());
        assertEquals(USER_ID_1, returnedConversations.get(0).getConversationPartnerId());
        assertEquals(USER_DISPLAY_NAME_1, returnedConversations.get(0).getConversationPartnerName());
        assertEquals(LAST_MESSAGE_1, returnedConversations.get(0).getLastMessage());
        assertEquals(LAST_MESSAGE_TIME_1.toString(), returnedConversations.get(0).getLastMessageTime());
    }
}
