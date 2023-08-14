package com.courseproject.tindar.controllers.conversationlist;

import com.courseproject.tindar.usecases.conversationlist.ConversationListInputBoundary;
import com.courseproject.tindar.usecases.conversationlist.ConversationResponseModel;

import java.util.ArrayList;

/**
 * Layer: Interface Adapter Layer - connects UI to Interactor
 *.
 * What is it :
 * receive UI request and communicate with Interactor
 * invoke the get active conversation on the interactor
 *.
 * Why does it exist:
 * The UI layer should be focused on presenting data and user interaction, while the use cases layer
 * handles the business logic
 * The controller acts as a bridge between UI and use case layers, ensuring a clear separation of
 * responsibilities.
 */
public class ConversationListController {


    /** The input boundary for conversation list operations. */
    private final ConversationListInputBoundary userInput;


    /**
     * Constructs a new ConversationListController with the provided ConversationListInputBoundary.
     *
     * @param conversationListUserInput The input boundary for conversation list operations.
     */
    public ConversationListController(ConversationListInputBoundary conversationListUserInput){
        this.userInput = conversationListUserInput;
    }

    /**
     * Retrieves an Arraylist of all active conversations for the specified user.
     *
     * @param userId The ID of the user for whom active conversations are to be retrieved.
     * @return An ArrayList of ConversationResponseModel representing the active conversations.
     */
    public ArrayList<ConversationResponseModel> getAllActiveConversations (String userId){
        //call function in interactor
        return userInput.getAllActiveConversations(userId);
    }
}