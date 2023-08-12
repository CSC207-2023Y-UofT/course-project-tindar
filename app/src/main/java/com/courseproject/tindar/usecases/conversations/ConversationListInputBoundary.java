package com.courseproject.tindar.usecases.conversations;

import java.util.ArrayList;

/**
 * What:
 *
 * Why does it exists:
 *
 *
 */

public interface ConversationListInputBoundary {
    // have same methods signature (implementation will be different) as controller

    ArrayList<ConversationResponseModel> getActiveConversations (String userId);
    // QUESTION: should it be updateAll Conversations?
}
