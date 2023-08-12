package com.courseproject.tindar.ui.conversationlist;


import com.courseproject.tindar.usecases.conversationlist.ConversationResponseModel;
import com.courseproject.tindar.usecases.conversations.ConversationListInputBoundary;

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
        return userInput.getActiveConversationIds(userId);
    }
}
