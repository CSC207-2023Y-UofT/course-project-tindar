package com.courseproject.tindar.usecases.conversations;

import java.util.ArrayList;

/**
 * What:
 *
 * Why does it exists:
 *
 *
 */

public class ConversationListInteractor implements ConversationListInputBoundary{
    // In user case layer
    // this class calls DB function to manipulate database / method that does data saving

    //getting information from db to pass to controller, controller pass it to UI
    //UI calls controller methods, and controller calls interactor
    //UI display data
    @Override
    public ArrayList<ConversationResponseModel> getActiveConversations(String userId) {
        return null;
    }
    //4
}
