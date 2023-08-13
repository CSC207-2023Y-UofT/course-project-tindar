package com.courseproject.tindar.usecases.chat;

import com.courseproject.tindar.entities.MessageModel;

import java.util.ArrayList;

public interface ChatDsGateway {

    String findConversationId(String userId1, String userId2);

    void addMessage(ChatRequestModel newMessage);

    ArrayList<MessageModel> readMessagesByConversationId(String conversationId);
}
