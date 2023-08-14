package com.courseproject.tindar.usecases.conversationlist;

import java.sql.Timestamp;

import lombok.Getter;

/**
 * Data source layer class representing a response model for a conversation message.
 *.
 * Layer: Data Source Layer
 *.
 * What is it:
 * This class encapsulates the data received as a response for a conversation message from the
 * database layer.
 * It defines fields to hold the last message content and its timestamp.
 *.
 * Why does it exist:
 * The ConversationMessageDsResponseModel exists to abstract and structure the response data for
 * conversation messages originating from the database.
 *
 */
public class ConversationMessageDsResponseModel {

    /**
     * The last message content.
     */
    @Getter String lastMessage;

    /**
     * The timestamp of the last message.
     */
    @Getter Timestamp lastMessageTime;

    /**
     * Constructor to create a ConversationMessageDsResponseModel instance.
     *
     * @param lastMessage The content of the last message.
     * @param lastMessageTime The timestamp of the last message.
     */
    public ConversationMessageDsResponseModel(String lastMessage, Timestamp lastMessageTime) {
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }
}
