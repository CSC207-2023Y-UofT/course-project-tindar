package com.courseproject.tindar.usecases.chat;

import java.sql.Timestamp;

import lombok.Getter;

/**
 * Models Tindar text messages which have not been processed by the database
 * (do not have message IDs).
 */
public class ChatRequestModel {
    @Getter private final String text;
    @Getter private final Timestamp creationTime;
    @Getter private final String sentFromId;
    @Getter private final String sentToId;
    @Getter private final String conversationId;

    /**
     * Creates a representation of a Tindar text message which still needs to be assigned a
     * message ID.
     * Note that all ID parameters should be integers represented as strings.
     * @param text the content of the message
     * @param timestamp the time at which this message was created
     * @param sentFromId the userID of the account that sent this message
     * @param sentToId the userID of the account receiving this message
     * @param conversationId the ID of the conversation where this message was sent
     */
    public ChatRequestModel(String text, Timestamp timestamp, String sentFromId, String sentToId,
                            String conversationId){
        this.text = text;
        this.creationTime = timestamp;
        this.sentFromId = sentFromId;
        this.sentToId = sentToId;
        this.conversationId = conversationId;
    }
}
