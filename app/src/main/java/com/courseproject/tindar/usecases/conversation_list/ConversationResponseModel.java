package com.courseproject.tindar.usecases.conversation_list;

public class ConversationResponseModel {
    private String userName;
    private String lastMessage;
    private String lastMessageTime;

    public ConversationResponseModel(String userName, String lastMessage, String lastMessageTime){
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;

    }


    public String getUserName() {
        return this.userName;
    }

    public String getLastMessage() {
        return this.lastMessage;
    }

    public String getLastMessageTime() {
        return this.lastMessageTime;
    }
}
