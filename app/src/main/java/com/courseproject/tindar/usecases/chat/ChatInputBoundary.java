package com.courseproject.tindar.usecases.chat;

import com.courseproject.tindar.entities.MessageModel;

import java.util.ArrayList;

/**
 * Creates and maintains an ArrayList of MessageModels for ChatActivity to display.
 */
public interface ChatInputBoundary {

    /**
     * Called whenever the user presses send on a non-empty message.
     * Adds the message to the database.
     * @param newMessageModel MessageModel representing the newly sent message
     */
    void sendMessage(ChatRequestModel newMessageModel);

    /**
     * Given userIds, returns a list of messages between the users in chronological order.
     * If no messages exist, then an empty list is returned.
     * @param conversationId conversation Id of the conversation that the user tries to get the messages from
     * @return a list of messages between the users in chronological order.
     *          If no messages exist, then an empty list is returned.
     */
    ArrayList<MessageModel> getMessageList(String conversationId);

    /**
     * @param userId the id of the user in the conversation
     * @param otherUserId the id of the other user in the conversation
     * @return conversation ID of the conversation that is being presented
     */
    String getConversationId(String userId, String otherUserId);
}
