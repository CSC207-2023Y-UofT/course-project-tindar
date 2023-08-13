package com.courseproject.tindar.entities;

import java.sql.Timestamp;

/**
 * Interface for message models containing the info that get passed to our Views
 */
public interface MessageModel {
    
    // Getter methods 

    /**
     * @return the messageId for this message. should be an integer represented as a string.
     */
    String getMessageId();

    /**
     * @return the time at which this message was created
     */
    Timestamp getCreationTime();

    /**
     * @return a string representing the content of this message
     */
    String getMessageContent();

    /**
     * @return  the userID of the one who sent this message.
     *          should be an integer represented as a string.
     */
    String getSentFromId();
    /**
     * @return  the userID of the account receiving this message.
     *          should be an integer represented as a string.
     */
    String getSentToId();

    /**
     * @return  the ID of the conversation where this message was sent.
     *          should be an integer represented as a string.
     */
    String getConversationId();
}
