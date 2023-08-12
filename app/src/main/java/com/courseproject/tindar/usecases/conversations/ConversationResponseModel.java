package com.courseproject.tindar.usecases.conversations;

/**
 * What: This class is responsible for getting the information needed to display on the conversation
 * list fragment for each conversation.
 *
 * Why do we need a another class and instance for information displayed on the screen from the
 * base:
 * Having another
 *
 */
public class ConversationResponseModel {
    private final String conversationId;
    private final String conversationPartnerName;
    private final String lastMessage;
    private final String lastMessageTime;

    public ConversationResponseModel(String conversationPartnerName, String lastMessage, String lastMessageTime,
                                     String conversationId){
        this.conversationPartnerName = conversationPartnerName;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.conversationId = conversationId;

    }

    public String getConversationPartnerName() {
        return this.conversationPartnerName;
    }

    public String getLastMessage() {
        return this.lastMessage;
    }

    public String getLastMessageTime() {
        return this.lastMessageTime;
    }
    public String getConversationId() {return this.conversationId; }
}
