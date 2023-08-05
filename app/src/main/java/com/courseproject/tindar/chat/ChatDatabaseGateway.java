package com.courseproject.tindar.chat;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface ChatDatabaseGateway {
    /*
    - future: mark conversation as inactive

    either add message should keep things in chronological order on some level,
    or the loader should be able to sort things
     */

    /**
     * Creates a new message record in the chat database using the IDs of the users involved.
     *
     * @param messageID a unique identifier for the message
     * @param messageContent string representing the message content
     * @param creationTime when the message was sent
     * @param senderID userID of the message sender
     * @param recipientID userID of the message recipient
     * @return true if successful; false otherwise (e.g. message already exists)
     */
    public abstract boolean addMessage(String messageID, String messageContent,
                                       Timestamp creationTime, String senderID,
                                       String recipientID);

    /**
     * Returns a list representing all messages in a given conversation
     *
     * @param user1 userID of alphabetically first userID for users in this conversation
     * @param user2 userID of alphabetically second userID for users in this conversation
     * @return
     */
    public abstract ArrayList<MessageModel> loadAllConversationMessages(String user1, String user2);

    /**
     * Returns a list representing all messages in a given conversation
     *
     * @param conversationID conversationID of the desired conversation
     * @return
     */
    public abstract ArrayList<MessageModel> loadAllConversationMessages(String conversationID);

    /**
     * Creates a new conversation record in the chat database
     *
     * @param user1 userID of alphabetically first userID for users in this conversation
     * @param user2 userID of alphabetically second userID for users in this conversation
     * @return true if conversation creation was successful (conversation did not already exist);
     *          false otherwise.
     */
    public abstract boolean addConversation(String user1, String user2);
    // should be called when a match is made

    /**
     *
     * @param userID the userID of the user whose list of conversations you want to retrieve
     * @return a list representing all conversations that the user is in
     */
    // public abstract ArrayList<ConversationModel> getConversationList(String userID);
}
