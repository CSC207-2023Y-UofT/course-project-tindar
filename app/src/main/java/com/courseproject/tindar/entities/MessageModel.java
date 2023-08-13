package com.courseproject.tindar.entities;

import java.sql.Timestamp;

/**
 * Interface for message models containing the info that get passed to our Views
 */
public interface MessageModel {
    
    // Getter methods 
    String getMessageId();
    Timestamp getCreationTime();
    String getMessageContent();
    String getSentFromId();
    String getSentToId();
    String getConversationId();

    // Setter methods--not implemented because they don't seem like they should exist
    /* 
    protected void setMessageId(String messageId);
    protected void setText(String text);
    protected void setTimestamp(Timestamp timestamp);
    protected void setSentFrom(String sentFrom);
    protected void setSentTo(String sentTo);
    */
}
