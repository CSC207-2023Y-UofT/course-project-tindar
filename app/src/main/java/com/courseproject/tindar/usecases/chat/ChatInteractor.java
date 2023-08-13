package com.courseproject.tindar.usecases.chat;

import com.courseproject.tindar.ds.DatabaseHelper;
import com.courseproject.tindar.entities.MessageModel;

import java.util.ArrayList;

public class ChatInteractor implements ChatPresenter, ChatActivityController{
    private final ChatDsGateway chatDsHelper;
    private final String userId;
    private String conversationPartnerUserId;
    private String[] userIds;
    private String conversationId;
    private ArrayList<MessageModel> messageList;
    private ArrayList<ChatPresenter> observers;

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

        this.conversationId = this.chatDsHelper.findConversationId(this.userIds[0], this.userIds[1]);
        this.updateMessageList();
        this.observers.add(this);
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
        for (ChatPresenter i: observers){
            i.updateMessageList();
        }
    }

    /**
     * Add object that should be notified when a message is sent to this conversation
     *
     * @param newObserver object that should be notified when a message is sent to this conversation
     */
    @Override
    public void addChatObserver(ChatPresenter newObserver) {
        if (!this.observers.contains(newObserver)) {
            this.observers.add(newObserver);
        };
    }

    /**
     * remove object from list of objects that should be notified of new messages
     *
     * @param observer object that should no longer be notified
     */
    @Override
    public void deleteChatObserver(ChatPresenter observer) {
        this.observers.remove(observer);
    }

    /**
     * Given userIds, returns a list of messages between the users in chronological order.
     * If no messages exist, then an empty list is returned.
     *
     * @return a list of messages between the users in chronological order.
     * If no messages exist, then an empty list is returned.
     */
    @Override
    public ArrayList<MessageModel> getMessageList() {
        return this.messageList;
    }

    /**
     * refreshes the message list based on what's currently in the database
     */
    @Override
    public void updateMessageList() {
        this.messageList = this.chatDsHelper.readMessagesByConversationId(this.conversationId);
    }

    /**
     * @return conversation ID of the conversation that is being presented
     */
    @Override
    public String getConversationId() {
        return this.conversationId;
    }
}
