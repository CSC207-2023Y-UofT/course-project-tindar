package com.courseproject.tindar.entities;

import java.sql.Timestamp;

/**
 * Currently models Tindar text messages. Implements MessageModel.
 */
 public class TindarMessage implements MessageModel {
    /**
     * the content of the message
     */
    private final String text;
    /**
     * the time at which this message was created
     */
    private final Timestamp creationTime;
    /**
     * the userID of the account that sent this message.
     * should be an integer represented as a string.
     */
    private final String sentFromId;
    /**
     * the userID of the account receiving this message.
     * should be an integer represented as a string.
     */
    private final String sentToId;
    /**
     * the unique ID representing this message.
     * should be an integer represented as a string.
     */
    private final String messageId;
    /**
     * the ID of the conversation where this message was sent.
     * should be an integer represented as a string.
     */
    private final String conversationId;

    /**
     * Creates a representation of a Tindar text message.
     * Note that all ID parameters should be integers represented as strings.
     * @param text the content of the message
     * @param timestamp the time at which this message was created
     * @param sentFromId the userID of the account that sent this message
     * @param sentToId the userID of the account receiving this message
     * @param messageId the unique ID representing this message
     * @param conversationId the ID of the conversation where this message was sent
     */
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

