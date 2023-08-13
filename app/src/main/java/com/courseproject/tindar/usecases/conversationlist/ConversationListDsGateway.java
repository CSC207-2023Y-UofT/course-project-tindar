package com.courseproject.tindar.usecases.conversationlist;

import java.util.ArrayList;

public interface ConversationListDsGateway {
    ArrayList<ConversationDsResponseModel> readConversationList(String userId);

    ConversationMessageDsResponseModel readLastMessage(String conversationId);

    ArrayList<String> readDisplayNamesForConversations(ArrayList<String> userIds);
}
