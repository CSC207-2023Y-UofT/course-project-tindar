package com.courseproject.tindar.usecases.chat;

import com.courseproject.tindar.entities.MessageModel;

import java.util.ArrayList;

/**
 * Interface for classes used to add messages to and load messages from the database.
 */
public interface ChatDsGateway {

    /**
     * Finds the ID of the conversation with these accounts.
     * Precondition: integer userId1 < integer userId2.
     * @param userId1 the numerically lesser of the two users.
     * @param userId2 the numerically greater of the two users.
     * @return a string of the integer ID of this conversation in the database.
     */
    String findConversationId(String userId1, String userId2);

    /**
     * Creates a new message record in the chat database.
     * Requires the message to not have an ID yet.
     *
     * @param newMessage representation of the new message that this method will record.
     */
    void addMessage(ChatRequestModel newMessage);

    /**
     * Returns a list representing all messages in a given conversation
     *
     * @param conversationId id of the conversation
     * @return a list representing all messages in this conversation. empty list if no messages.
     */
    ArrayList<MessageModel> readMessagesByConversationId(String conversationId);
}
