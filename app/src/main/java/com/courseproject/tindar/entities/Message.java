package com.courseproject.tindar.entities;

import java.sql.Timestamp;

 public   class Message {
    private String messageId;
    private String text;
    private Timestamp creationTime;
    private String sentFromId;
    private String sentToId;

    protected Message(String text, Timestamp timestamp, String sentFromId, String sentToId){
        this.text = text; 
        this.timestamp = timestamp; 
        this.sentFromId = sentFromId; 
        this.sentToId = sentToId;
        this.messageId = sentFromId + sentToId + timestamp.toString(); 
        //implementation of messageId will probably be changed
    }

    // Getter methods
    public String getMessageId() {
        return this.messageId;
    }

    public String getMessageContent() {     
        return this.text;
    }

    public Timestamp getCreationTime() {
        return this.creationTime;
    }

    public String getSentFromId() {
        return this.sentFromId;
    }

    public String getSentToId() {
        return this.sentToId;
    }

    // Setter methods
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
}

