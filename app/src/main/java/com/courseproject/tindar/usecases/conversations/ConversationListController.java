package com.courseproject.tindar.usecases.conversations;


import com.courseproject.tindar.usecases.conversations.ConversationListInputBoundary;
import com.courseproject.tindar.usecases.conversations.ConversationResponseModel;

import java.util.ArrayList;

public class ConversationListController {
    //
    // Takes user input frontend and pass it onto Interactor

    ConversationListInputBoundary userInput;


    public ConversationListController(ConversationListInputBoundary conversationListUserInput){
        this.userInput = conversationListUserInput;
    }

    public ArrayList<ConversationResponseModel> getActiveConversations (String userId){
        //call function in interactor
        return userInput.getActiveConversations(userId);
    }
}
