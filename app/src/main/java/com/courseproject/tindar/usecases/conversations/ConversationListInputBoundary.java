package com.courseproject.tindar.usecases.conversations;

import java.util.ArrayList;

public interface ConversationListInputBoundary {
    // have same methods signature (implemenation will be different) as cntroller

    ArrayList<ConversationResponseModel> getActiveConversations (String userId);
}
