package com.courseproject.tindar.usecases.conversationlist;

import java.sql.Timestamp;

import lombok.Getter;

public class ConversationMessageDsResponseModel {
    @Getter
    final String lastMessage;
    @Getter
    final Timestamp lastMessageTime;

    public ConversationMessageDsResponseModel(String lastMessage, Timestamp lastMessageTime) {
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }
}
