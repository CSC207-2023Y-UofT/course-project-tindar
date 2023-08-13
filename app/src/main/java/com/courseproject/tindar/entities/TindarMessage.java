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

    // Getter methods
    @Override
    public String getMessageId() {
        return this.messageId;
    }

    @Override
    public String getMessageContent() {
        return this.text;
    }

    @Override
    public Timestamp getCreationTime() {
        return this.creationTime;
    }

    @Override
    public String getSentFromId() {
        return this.sentFromId;
    }

    @Override
    public String getSentToId() {
        return this.sentToId;
    }


    @Override
    public String getConversationId() {return this.conversationId;}

    // Setter methods
    /*
     @Override
     public String getConversationId() {return this.conversationId;}

     // Setter methods
    /*
    protected void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    protected void setText(String text) {
        this.text = text;
    }

    protected void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    protected void setSentFrom(String sentFrom) {
        this.sentFromId = sentFromId;
    }

    protected void setSentTo(String sentTo) {
        this.sentToId = sentToId;
    }
    */
}

