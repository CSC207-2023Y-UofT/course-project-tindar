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
    //
    // Takes user input frontend and pass it onto Interactor

    ConversationListInputBoundary userInput;


    public ConversationListController(ConversationListInputBoundary conversationListUserInput){
        this.userInput = conversationListUserInput;
    }

    public ArrayList<ConversationResponseModel> getAllActiveConversations (String userId){
        //call function in interactor
        return userInput.getAllActiveConversations(userId);
    }
}