package com.courseproject.tindar.usecases.chat;

import com.courseproject.tindar.entities.MessageModel;

import java.util.ArrayList;

/**
 * Creates and maintains an ArrayList of MessageModels for ChatActivity to display.
 */
public interface ChatPresenter {
    /**
     * Given userIds, returns a list of messages between the users in chronological order.
     * If no messages exist, then an empty list is returned.
     * @return a list of messages between the users in chronological order.
     *          If no messages exist, then an empty list is returned.
     */
    ArrayList<MessageModel> getMessageList();

    /**
     * refreshes the message list based on what's currently in the database
     */
    void updateMessageList();

    /**
     * @return conversation ID of the conversation that is being presented
     */
    String getConversationId();
}
