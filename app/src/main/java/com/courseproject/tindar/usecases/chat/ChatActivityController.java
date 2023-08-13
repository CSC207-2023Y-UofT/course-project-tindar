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
    void sendMessage(MessageModel newMessageModel);
}
