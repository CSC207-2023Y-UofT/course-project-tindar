package com.courseproject.tindar.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.sql.Timestamp;

public class TindarMessageUnitTest {
    private static final String CONVERSATION_ID = "7";
    private static final String MESSAGE_ID = "101";

    private static final String USER_ID_1 = "1";
    private static final String USER_ID_2 = "2";
    private static final String MESSAGE_TEXT = "first message sent";
    private static final Timestamp MESSAGE_CREATION_TIME = java.sql.Timestamp.valueOf("2005-04-06 09:01:10");

    private static final MessageModel MESSAGE = new TindarMessage(MESSAGE_TEXT,
            MESSAGE_CREATION_TIME, USER_ID_1, USER_ID_2, MESSAGE_ID,
            CONVERSATION_ID);

    @Test
    public void testGetMessageContent() {
        assertEquals(MESSAGE_TEXT, MESSAGE.getMessageContent());
    }

    @Test
    public void testGetCreationTime() {
        assertEquals(MESSAGE_CREATION_TIME, MESSAGE.getCreationTime());
    }

    @Test
    public void testGetSentFromId() {
        assertEquals(USER_ID_1, MESSAGE.getSentFromId());
    }

    @Test
    public void testGetSentToId() {
        assertEquals(USER_ID_2, MESSAGE.getSentToId());
    }

    @Test
    public void testGetMessageId() {
        assertEquals(MESSAGE_ID, MESSAGE.getMessageId());
    }

    @Test
    public void testGetConversationId() {
        assertEquals(CONVERSATION_ID, MESSAGE.getConversationId());
    }
}
