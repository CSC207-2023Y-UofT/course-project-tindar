package com.courseproject.tindar.entities;

import java.sql.Timestamp;

public class Message {
    private String messageID;
    private String text;
    private Timestamp timestamp;
    private String sentFromID;
    private String sentToID;

    protected Message(String text, Timestamp timestamp, String sentFromID, String sentToID){
        this.text = text; 
        this.timestamp = timestamp; 
        this.sentFromID = sentFromID; 
        this.sentToID = sentToID;
        this.messageID = sentFromID + sentToID + timestamp.toString(); 
        //implementation of messageID will probably be changed
    }

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

    public String getSentFromID() {
        return this.sentFromID;
    }

    public String getSentToID() {
        return this.sentToID;
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
        this.sentFrom = sentFromID;
    }

    protected void setSentTo(String sentTo) {
        this.sentTo = sentToID;
    }
}

