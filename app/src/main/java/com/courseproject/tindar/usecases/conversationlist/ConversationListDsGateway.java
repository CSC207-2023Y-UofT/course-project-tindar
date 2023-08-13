package com.courseproject.tindar.usecases.conversationlist;

import java.util.ArrayList;

public interface ConversationListDsGateway {
    void addConversation(String userId, String otherUserId);
    ArrayList<String[]> readConversationList(String userId);

    ConversationMessageDsResponseModel readLastMessage(String conversationId);

    ArrayList<String> readDisplayNamesForConversations(ArrayList<String> userIds);
}
