package com.courseproject.tindar.controllers.chat;

import com.courseproject.tindar.entities.MessageModel;
import com.courseproject.tindar.usecases.chat.ChatInputBoundary;
import com.courseproject.tindar.usecases.chat.ChatRequestModel;

import java.util.ArrayList;

public class ChatController {

    private final ChatInputBoundary userInput;

    public ChatController(ChatInputBoundary chatUserInput) {
        this.userInput = chatUserInput;
    }

    /**
     * Called whenever the user presses send on a non-empty message.
     * Adds the message to the database.
     *
     * @param newMessageModel MessageModel representing the newly sent message
     */
    public void sendMessage(ChatRequestModel newMessageModel) {
        this.userInput.sendMessage(newMessageModel);
    }

    /**
     * Given conversation id, returns a list of messages between the users in chronological order.
     * If no messages exist, then an empty list is returned.
     *
     * @param conversationId conversation Id of the conversation that the user tries to get the messages from
     * @return a list of messages between the users in chronological order.
     * If no messages exist, then an empty list is returned.
     */
    public ArrayList<MessageModel> getMessageList(String conversationId) {
        return this.userInput.getMessageList(conversationId);
    }

    /**
     * @param userId the id of the user in the conversation
     * @param otherUserId the id of the other user in the conversation
     * @return conversation Id of the conversation that is being presented
     */
    public String getConversationId(String userId, String otherUserId) {
        return this.userInput.getConversationId(userId, otherUserId);
    }
}
