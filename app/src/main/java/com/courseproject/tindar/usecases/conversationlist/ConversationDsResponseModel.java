package com.courseproject.tindar.usecases.conversationlist;

import lombok.Getter;

    /**
     * Response Model for the data saving layer, information directed to the database
      */
public class ConversationDsResponseModel {
    @Getter private final String conversationId;
    @Getter private final String userId1;
    @Getter private final String userId2;

    /**
     *Constructs a new ConversationDsResponseModel.*
     *@param userId1       User ID of the first participant
     *@param userId2       User ID of the second participant
     */
    public ConversationDsResponseModel(String conversationId, String userId1, String userId2){
        this.conversationId = conversationId;
        this.userId1 = userId1;
        this.userId2 = userId2;
    }
}