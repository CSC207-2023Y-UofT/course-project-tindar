package com.courseproject.tindar.usecases.conversations;

import com.courseproject.tindar.usecases.conversationlist.ConversationResponseModel;

import java.util.ArrayList;

public interface ConversationListInputBoundary {
    // have same methods signature (implementation will be different) as controller

    ArrayList<ConversationResponseModel> getActiveConversationIds(String userId);
}
