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
     * Add object that should be notified when a message is sent to this conversation
     *
     * @param newObserver object that should be notified when a message is sent to this conversation
     */
    @Override
    public void addChatObserver(ChatPresenter newObserver) {

    }

    /**
     * remove object from list of objects that should be notified of new messages
     *
     * @param observer object that should no longer be notified
     */
    @Override
    public void deleteChatObserver(ChatPresenter observer) {

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

    /**
     * Adds a message to the message list.
     *
     * @param newMessage new message to be added
     */
    @Override
    public void updateMessageList(MessageModel newMessage) {

    }

    private void notifyObservers(){
        return;
    }
}
