package com.courseproject.tindar.entities;

import java.sql.Timestamp;

public class Message {
    private String messageID;
    private String text;
    private Timestamp timestamp;
    private String sentFrom;
    private String sentTo;

    // Getter methods
    public String getMessageID() {
        return this.messageID;
    }

    public String getMessageContent() {     
        return this.text;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public String getSentFrom() {
        return this.sentFrom;
    }

    public String getSentTo() {
        return this.sentTo;
    }

    // Setter methods
    protected void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    protected void setText(String text) {
        this.text = text;
    }

    protected void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    protected void setSentFrom(String sentFrom) {
        this.sentFrom = sentFrom;
    }

    protected void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }
}

