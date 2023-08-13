package com.courseproject.tindar.usecases.chat;

import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.entities.MessageModel;

import java.util.ArrayList;

public class ChatInteractor implements ChatPresenter, ChatActivityController{
    private final ChatDsGateway chatDsHelper;
    private final String userId;
    private String conversationPartnerUserId;
    private String[] userIds;
    private ArrayList<MessageModel> messageList;

    public ChatInteractor(ChatDsGateway chatDsHelper, String userId,
                          String conversationPartnerUserId){
        this.chatDsHelper = chatDsHelper;
        this.userId = userId;
        this.conversationPartnerUserId = conversationPartnerUserId;
        this.messageList = new ArrayList<>();

        if(Integer.parseInt(this.userId) < Integer.parseInt(this.conversationPartnerUserId)){
            String[] userIds = {userId, conversationPartnerUserId};
        } else {
            String[] userIds = {conversationPartnerUserId, userId};
        }
    }

    /**
     * Called whenever the user presses send on a non-empty message.
     * Adds the message to the database and notifies observers.
     *
     * @param newMessageModel MessageModel representing the newly sent message
     */
    @Override
    public void sendMessage(ChatRequestModel newMessageModel) {
        this.chatDsHelper.addMessage(newMessageModel);
    }

    /**
     * Given userIds, returns a list of messages between the users in chronological order.
     * If no messages exist, then an empty list is returned.
     *
     * @param userIds the userIds of the users in the conversation.
     * @return a list of messages between the users in chronological order.
     * If no messages exist, then an empty list is returned.
     */
    @Override
    public ArrayList<MessageModel> getMessageList(String[] userIds) {
        return this.messageList;
    }
}
