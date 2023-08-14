package com.courseproject.tindar.entities;

import java.sql.Timestamp;

import lombok.Getter;

/**
 * Models Tindar text messages which have been processed by the database (have message IDs).
 * Implements MessageModel.
 */
 public class TindarMessage implements MessageModel {
    /**
     * the content of the message
     */
    @Getter private final String messageContent;
    /**
     * the time at which this message was created
     */
    @Getter private final Timestamp creationTime;
    /**
     * the userID of the account that sent this message.
     * should be an integer represented as a string.
     */
    @Getter private final String sentFromId;
    /**
     * the userID of the account receiving this message.
     * should be an integer represented as a string.
     */
    @Getter private final String sentToId;
    /**
     * the unique ID representing this message.
     * should be an integer represented as a string.
     */
    @Getter private final String messageId;
    /**
     * the ID of the conversation where this message was sent.
     * should be an integer represented as a string.
     */
    @Getter private final String conversationId;

    /**
     * Creates a representation of a Tindar text message.
     * Note that all ID parameters should be integers represented as strings.
     * @param messageContent the content of the message
     * @param timestamp the time at which this message was created
     * @param sentFromId the userID of the account that sent this message
     * @param sentToId the userID of the account receiving this message
     * @param messageId the unique ID representing this message
     * @param conversationId the ID of the conversation where this message was sent
     */
    public TindarMessage(String messageContent, Timestamp timestamp, String sentFromId, String sentToId,
                         String messageId, String conversationId){
        this.messageContent = messageContent;
        this.creationTime = timestamp;
        this.sentFromId = sentFromId;
        this.sentToId = sentToId;
        this.messageId = messageId;
        this.conversationId = conversationId;
    }
}

