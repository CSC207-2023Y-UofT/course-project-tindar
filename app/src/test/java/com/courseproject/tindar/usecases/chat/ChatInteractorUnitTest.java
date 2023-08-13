package com.courseproject.tindar.usecases.chat;

import static org.junit.Assert.assertEquals;

import com.courseproject.tindar.entities.MessageModel;
import com.courseproject.tindar.entities.TindarMessage;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ChatInteractorUnitTest {

    private static final String CONVERSATION_ID = "7";
    private static final String MESSAGE_ID = "101";

    private static final String USER_ID_1 = "1";
    private static final String USER_ID_2 = "2";
    private static final String MESSAGE_TEXT = "first message sent";
    private static final Timestamp MESSAGE_CREATION_TIME = java.sql.Timestamp.valueOf("2005-04-06 09:01:10");

    private static final ChatRequestModel SENT_MESSAGE = new ChatRequestModel(MESSAGE_TEXT,
            MESSAGE_CREATION_TIME, USER_ID_1, USER_ID_2, CONVERSATION_ID);

    private static final MessageModel MESSAGE = new TindarMessage(MESSAGE_TEXT,
            MESSAGE_CREATION_TIME, USER_ID_1, USER_ID_2, MESSAGE_ID,
            CONVERSATION_ID);

    private static class MockChatDsGateway implements ChatDsGateway {
        @Override
        public String findConversationId(String userId1, String userId2) {
            assertEquals(USER_ID_1, userId1);
            assertEquals(USER_ID_2, userId2);
            return CONVERSATION_ID;
        }

        @Override
        public void addMessage(ChatRequestModel newMessage) {
            assertEquals(MESSAGE_TEXT, newMessage.getText());
            assertEquals(CONVERSATION_ID, newMessage.getConversationId());
            assertEquals(MESSAGE_CREATION_TIME, newMessage.getCreationTime());
            assertEquals(USER_ID_1, newMessage.getSentFromId());
            assertEquals(USER_ID_2, newMessage.getSentToId());
        }

        @Override
        public ArrayList<MessageModel> readMessagesByConversationId(String conversationId) {
            assertEquals(CONVERSATION_ID, conversationId);
            ArrayList<MessageModel> messages = new ArrayList<>();
            messages.add(MESSAGE);
            return messages;
        }
    }

    @Test
    public void testFindConversationId() {
        ChatDsGateway testChatDbHelper = new MockChatDsGateway();
        ChatInputBoundary testChatInteractor = new ChatInteractor(testChatDbHelper);
        testChatInteractor.getConversationId(USER_ID_1, USER_ID_2);
    }

    @Test
    public void testFindConversationIdUserIdReversed() {
        ChatDsGateway testChatDbHelper = new MockChatDsGateway();
        ChatInputBoundary testChatInteractor = new ChatInteractor(testChatDbHelper);
        testChatInteractor.getConversationId(USER_ID_2, USER_ID_1);
    }

    @Test
    public void testSendMessage() {
        ChatDsGateway testChatDbHelper = new MockChatDsGateway();
        ChatInputBoundary testChatInteractor = new ChatInteractor(testChatDbHelper);
        testChatInteractor.sendMessage(SENT_MESSAGE);
    }

    @Test
    public void testGetMessageList() {
        ChatDsGateway testChatDbHelper = new MockChatDsGateway();
        ChatInputBoundary testChatInteractor = new ChatInteractor(testChatDbHelper);
        ArrayList<MessageModel> messageList = testChatInteractor.getMessageList(CONVERSATION_ID);
        assertEquals(1, messageList.size());
        assertEquals(MESSAGE_TEXT, messageList.get(0).getMessageContent());
        assertEquals(MESSAGE_CREATION_TIME, messageList.get(0).getCreationTime());
        assertEquals(USER_ID_1, messageList.get(0).getSentFromId());
        assertEquals(USER_ID_2, messageList.get(0).getSentToId());
        assertEquals(MESSAGE_ID, messageList.get(0).getMessageId());
        assertEquals(CONVERSATION_ID, messageList.get(0).getConversationId());
    }
}
