package com.courseproject.tindar.entities;

import java.sql.Timestamp;

public class Message {
    private String messageID;
    private String text;
    private Timestamp timestamp;
    private String sentFrom;
    private String sentTo;
    private String conversationId;

    // Getter methods
    public String getMessageID() {
        return messageID;
    }

    public String getText() {
        return text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getSentFrom() {
        return sentFrom;
    }

    public String getSentTo() {
        return sentTo;
    }

    public String getConversationId() {
        return conversationId;
    }

    // Setter methods
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setSentFrom(String sentFrom) {
        this.sentFrom = sentFrom;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}

