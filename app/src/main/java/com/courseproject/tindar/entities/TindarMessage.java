package com.courseproject.tindar.entities;

import java.sql.Timestamp;

/**
 * Currently models Tindar text messages. Implements MessageModel.
 */
 public class TindarMessage implements MessageModel {
    private final String text;
    private final Timestamp creationTime;
    private final String sentFromId;
    private final String sentToId;
    private final String messageId;
    private final String conversationId;

    public TindarMessage(String text, Timestamp timestamp, String sentFromId, String sentToId,
                         String messageId, String conversationId){
        this.text = text;
        this.creationTime = timestamp;
        this.sentFromId = sentFromId;
        this.sentToId = sentToId;
        this.messageId = messageId;
        this.conversationId = conversationId;
    }

    /**
     * @return the messageId for this message. should be an integer represented as a string.
     */
    @Override
    public String getMessageId() {
        return this.messageId;
    }

    /**
     * @return the time at which this message was created
     */
    @Override
    public Timestamp getCreationTime() {
        return this.creationTime;
    }

    /**
     * @return a string representing the content of this message
     */
    @Override
    public String getMessageContent() {
        return this.text;
    }

    /**
     * @return the userID of the one who sent this message.
     * should be an integer represented as a string.
     */
    @Override
    public String getSentFromId() {
        return this.sentFromId;
    }

    /**
     * @return  the userID of the account receiving this message.
     *          should be an integer represented as a string.
     */
    @Override
    public String getSentToId() {
        return this.sentToId;
    }

    /**
     * @return  the ID of the conversation where this message was sent.
     *          should be an integer represented as a string.
     */
    @Override
    public String getConversationId() {
        return this.conversationId;
    }
}

