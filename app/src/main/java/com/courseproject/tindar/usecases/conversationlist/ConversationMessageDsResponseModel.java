package com.courseproject.tindar.usecases.conversationlist;

import java.sql.Timestamp;

import lombok.Getter;

public class ConversationMessageDsResponseModel {
    @Getter String lastMessage;
    @Getter Timestamp lastMessageTime;

    public ConversationMessageDsResponseModel(String lastMessage, Timestamp lastMessageTime) {
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }
}
