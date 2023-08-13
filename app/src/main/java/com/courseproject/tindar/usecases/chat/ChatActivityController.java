package com.courseproject.tindar.usecases.chat;

import com.courseproject.tindar.entities.MessageModel;

/**
 * For acting on the user's actions on ChatActivity.
 */
public interface ChatActivityController {
    // should have a method called when something is sent

    /**
     * Called whenever the user presses send on a non-empty message.
     * Adds the message to the database and notifies observers.
     */
    void sendMessage(ChatRequestModel newMessageModel);

    /**
     * Add object that should be notified when a message is sent to this conversation
     * @param newObserver object that should be notified when a message is sent to this conversation
     */
    void addChatObserver(ChatPresenter newObserver);

    /**
     * remove object from list of objects that should be notified of new messages
     * @param observer object that should no longer be notified
     */
    void deleteChatObserver(ChatPresenter observer);
}
