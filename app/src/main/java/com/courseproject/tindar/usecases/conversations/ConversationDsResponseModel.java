package com.courseproject.tindar.usecases.conversations;

import lombok.Getter;


/**
 * Layer : Entity layer for passing information into database
 *.
 * What:
 *  Response model representing a conversation entity in the data source layer.
 *  This model holds information to be directed to the database for storage.
 *.
 * Why does it exists:
 * It exist to meet the needs of the data storage operations and interactions,
 * providing a clear separation between the database layer and the rest of the application.
 *
 */

public class ConversationDsResponseModel {
    // Response Model for the data saving layer, information directed to the database
        /**
         * Unique identifier for the conversation.
         */
        @Getter private final String conversationId;

        /**
         * User ID of the first participant in the conversation.
         */
        @Getter private final String userId1;

        /**
         * User ID of the second participant in the conversation.
         */
        @Getter private final String userId2;

        /**
         * Constructs a new ConversationDsResponseModel.
         *
         * @param userId1       User ID of the first participant
         * @param userId2       User ID of the second participant
         * @param conversationId Unique identifier for the conversation
         */
    @Getter private final String conversationId;
    @Getter private final String userId1;
    @Getter private final String userId2;

    public ConversationDsResponseModel(String userId1, String userId2, String conversationId){
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.conversationId = conversationId;
    }


}
