package com.courseproject.tindar.usecases.conversationlist;

import java.util.ArrayList;

/**
 * Layers: Use Case - acts as a contract between use cases and outer layers
 * .
 * What is it :
 * The input boundary specifies the methods that the controller use to interact with the use cases.
 * .
 * Why does it exist:
 * it acts as an abstraction that ensures the UI layer doesn't need to know the implementation
 * details of the use cases but can still invoke them with the required input.
 */

public interface ConversationListInputBoundary {
    // have same methods signature (implementation will be different) as controller

    /**
     * Retrieves an ArrayList of ConversationResponseModel representing active conversations
     * for a given user.
     *
     * @param userId The user ID for which to fetch active conversations.
     * @return An ArrayList of ConversationResponseModel representing active conversations.
     */
    ArrayList<ConversationResponseModel> getAllActiveConversations (String userId);
}