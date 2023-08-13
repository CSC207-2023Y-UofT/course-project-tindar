package com.courseproject.tindar.controllers.chat;

import static org.junit.Assert.assertEquals;

import com.courseproject.tindar.entities.MessageModel;
import com.courseproject.tindar.entities.TindarMessage;
import com.courseproject.tindar.usecases.chat.ChatInputBoundary;
import com.courseproject.tindar.usecases.chat.ChatRequestModel;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ChatControllerUnitTest {

    private static final String USER_ID_1 = "user1";
    private static final String USER_ID_2 = "user2";
    private static final String CONVERSATION_ID = "conversation1";
    private static final String MESSAGE_ID = "message1";
    private static final String MESSAGE_TEXT = "first message sent";
    private static final Timestamp MESSAGE_CREATION_TIME = java.sql.Timestamp.valueOf("2005-04-06 09:01:10");

    private static final ChatRequestModel SENT_MESSAGE = new ChatRequestModel(MESSAGE_TEXT ,
            MESSAGE_CREATION_TIME, USER_ID_1, USER_ID_2, CONVERSATION_ID);

    private static final MessageModel MESSAGE = new TindarMessage(MESSAGE_TEXT,
            MESSAGE_CREATION_TIME, USER_ID_1, USER_ID_2, MESSAGE_ID,
            CONVERSATION_ID);

    public static class MockChatUserInput implements ChatInputBoundary {
        @Override
        public void sendMessage(ChatRequestModel newMessage) {
            assertEquals(MESSAGE_TEXT, newMessage.getText());
            assertEquals(CONVERSATION_ID, newMessage.getConversationId());
            assertEquals(MESSAGE_CREATION_TIME, newMessage.getCreationTime());
            assertEquals(USER_ID_1, newMessage.getSentFromId());
            assertEquals(USER_ID_2, newMessage.getSentToId());
        }

        @Override
        public ArrayList<MessageModel> getMessageList(String conversationId) {
            assertEquals(CONVERSATION_ID, conversationId);
            ArrayList<MessageModel> messages = new ArrayList<>();
            messages.add(MESSAGE);
            return messages;
        }

        @Override
        public String getConversationId(String userId, String otherUserId) {
            assertEquals(USER_ID_1, userId);
            assertEquals(USER_ID_2, otherUserId);
            return CONVERSATION_ID;
        }
    }

    @Test
    public void testSendMessage() {
        ChatInputBoundary mockChatUserInput = new MockChatUserInput();
        ChatController testChatController = new ChatController(mockChatUserInput);
        testChatController.sendMessage(SENT_MESSAGE);

    }

    @Test
    public void testGetMessageList() {
        ChatInputBoundary mockChatUserInput = new MockChatUserInput();
        ChatController testChatController = new ChatController(mockChatUserInput);
        ArrayList<MessageModel> messageList = testChatController.getMessageList(CONVERSATION_ID);
        assertEquals(1, messageList.size());
        assertEquals(MESSAGE_TEXT, messageList.get(0).getMessageContent());
        assertEquals(MESSAGE_CREATION_TIME, messageList.get(0).getCreationTime());
        assertEquals(USER_ID_1, messageList.get(0).getSentFromId());
        assertEquals(USER_ID_2, messageList.get(0).getSentToId());
        assertEquals(MESSAGE_ID, messageList.get(0).getMessageId());
        assertEquals(CONVERSATION_ID, messageList.get(0).getConversationId());
    }

    @Test
    public void getGetConversationId() {
        ChatInputBoundary mockChatUserInput = new MockChatUserInput();
        ChatController testChatController = new ChatController(mockChatUserInput);
        testChatController.getConversationId(USER_ID_1, USER_ID_2);
    }
}
