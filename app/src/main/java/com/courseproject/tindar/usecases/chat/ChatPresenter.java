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
     * @param userIds the userIds of the users in the conversation.
     * @return a list of messages between the users in chronological order.
     *          If no messages exist, then an empty list is returned.
     */
    ArrayList<MessageModel> getMessageList(String[] userIds);

    /**
     * Adds a message to the message list.
     * @param newMessage new message to be added
     */
    void updateMessageList(MessageModel newMessage);
}
