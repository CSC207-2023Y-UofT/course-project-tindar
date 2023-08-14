package com.courseproject.tindar.usecases.chat;

import com.courseproject.tindar.entities.MessageModel;

import java.util.ArrayList;

/**
 * Implements the changes requested by the controller
 */
public class ChatInteractor implements ChatInputBoundary {
    /**
     * the database helper used to update the databse
     */
    private final ChatDsGateway chatDsHelper;

    /**
     * Creates a ChatInteracter object.
     * @param chatDsHelper the ChatDsGateway that this interactor uses to update the database.
     */
    public ChatInteractor(ChatDsGateway chatDsHelper){
        this.chatDsHelper = chatDsHelper;
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
     * @param conversationId conversation Id of the conversation that the user tries to get the messages from
     * @return a list of messages between the users in chronological order.
     * If no messages exist, then an empty list is returned.
     */
    @Override
    public ArrayList<MessageModel> getMessageList(String conversationId) {
        return this.chatDsHelper.readMessagesByConversationId(conversationId);
    }

    /**
     * @param userId the id of the user in the conversation
     * @param otherUserId the id of the other user in the conversation
     * @return conversation ID of the conversation that is being presented
     */
    @Override
    public String getConversationId(String userId, String otherUserId) {
        if (Integer.parseInt(userId) < Integer.parseInt(otherUserId)) {
            return this.chatDsHelper.findConversationId(userId, otherUserId);
        } else {
            return this.chatDsHelper.findConversationId(otherUserId, userId);
        }
    }
}
