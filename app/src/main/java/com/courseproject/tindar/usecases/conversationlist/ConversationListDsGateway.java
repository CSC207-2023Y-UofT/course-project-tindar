package com.courseproject.tindar.usecases.conversationlist;

import java.util.ArrayList;

/**
 * Interface representing the data source gateway for conversation list data.
 */
public interface ConversationListDsGateway {

    /**
     * Reads and retrieves a list of conversation response models for the given user ID.
     *
     * @param userId The user ID for which to retrieve conversation list.
     * @return An ArrayList of ConversationDsResponseModel representing conversation data.
     */
    ArrayList<ConversationDsResponseModel> readConversationList(String userId);

    /**
     * Reads and retrieves the last message of a conversation.
     *
     * @param conversationId The ID of the conversation to retrieve the last message from.
     * @return A ConversationMessageDsResponseModel representing the last message.
     */
    ConversationMessageDsResponseModel readLastMessage(String conversationId);

    /**
     * Reads and retrieves display names for a list of user IDs.
     *
     * @param userIds An ArrayList of user IDs for which to retrieve display names.
     * @return An ArrayList of Strings representing display names.
     */
    ArrayList<String> readDisplayNames(ArrayList<String> userIds);
}
