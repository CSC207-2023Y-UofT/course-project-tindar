package com.courseproject.tindar.usecases.chat;

import java.sql.Timestamp;

import lombok.Getter;

public class ChatRequestModel {
    @Getter private final String text;
    @Getter private final Timestamp creationTime;
    @Getter private final String sentFromId;
    @Getter private final String sentToId;
    @Getter private final String conversationId;

    public ChatRequestModel(String text, Timestamp timestamp, String sentFromId, String sentToId,
                            String conversationId){
        this.text = text;
        this.creationTime = timestamp;
        this.sentFromId = sentFromId;
        this.sentToId = sentToId;
        this.conversationId = conversationId;
    }
}
