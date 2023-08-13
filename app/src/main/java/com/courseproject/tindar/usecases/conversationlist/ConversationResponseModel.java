package com.courseproject.tindar.usecases.conversationlist;

/**
 * Layer: entity Layer
 * What:
 * An entity layer / class that is responsible for containing the information needed to display on the
 * conversation list fragment in UI layer for each conversation.
 *.
 * Why do we need a another class and instance for information displayed on the screen from the
 * base:
 * Having another class that acts as a data transformation model that encapsulates the
 * necessary data needed for displaying conversations on the UI.
 * This helps to decouple the domain/business logic from the specific requirements of the UI
 *
 */
public class ConversationResponseModel {
    private final String conversationPartnerId;
    private final String conversationPartnerName;
    private final String lastMessage;
    private final String lastMessageTime;
    // due to debugging and time constraints the lastMessage and last MessageTime will not
    //be implemented

    /**
     * Constructs a new ConversationResponseModel object with the provided details.
     *
     * @param conversationPartnerId
     * @param conversationPartnerName the display name of the conversation partner
     *                                // @param lastMessage a preview of their last message
     *                                // @param lastMessageTime a time stamp of their last message
     * @param lastMessage
     * @param lastMessageTime
     */
    public ConversationResponseModel(String conversationPartnerId, String conversationPartnerName, String lastMessage, String lastMessageTime){
        this.conversationPartnerId = conversationPartnerId;
        this.conversationPartnerName = conversationPartnerName;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }

    /**
     * Gets the user id of the conversation partner.
     *
     * @return The user id of the conversation partner.
     */
    public String getConversationPartnerId() {
        return this.conversationPartnerId;
    }

    /**
     * Gets the name of the conversation partner.
     *
     * @return The name of the conversation partner.
     */
    public String getConversationPartnerName() {
        return this.conversationPartnerName;
    }

    /**
     * Gets the content of the last message in the conversation.
     *
     * @return The content of the last message.
     */
    public String getLastMessage() {
        return this.lastMessage;
    }

    /**
     * Gets the timestamp of the last message.
     *
     * @return The timestamp of the last message.
     */
    public String getLastMessageTime() {
        return this.lastMessageTime;
    }
}