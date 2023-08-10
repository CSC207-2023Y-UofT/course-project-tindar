package com.courseproject.tindar.usecases.conversations;

import lombok.Getter;

public class ConversationDsResponseModel {
    // Response Model for the data saving layer, information directed to the database

    @Getter private final String conversationId;

    @Getter private final String userId1;
    @Getter private final String userId2;

    public ConversationDsResponseModel(String userId1, String userId2, String conversationId){
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.conversationId = conversationId;
    }


}
