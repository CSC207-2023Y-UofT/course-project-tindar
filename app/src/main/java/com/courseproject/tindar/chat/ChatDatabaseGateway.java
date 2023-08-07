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
     * Requires the messageID to have already been created.
     *
     * @param newMessage representation of the new message that this method will record.
     * @return true if successful; false otherwise (e.g. message already exists)
     */
    boolean addMessage(MessageModel newMessage);

    /**
     * @param messageID messageID of the desired message
     * @return representation of desired message if found; null otherwise
     */
    MessageModel getMessage(String messageID);

    /**
     * Returns a list representing all messages in a given conversation
     *
     * @param users userIDs of users in this conversation
     * @return
     */
    ArrayList<MessageModel> loadAllConversationMessages(String[] users);

    /**
     * Returns a list representing all messages in a given conversation
     *
     * @param conversationID conversationID of the desired conversation
     * @return
     */
    ArrayList<MessageModel> loadAllConversationMessages(String conversationID);

    // Conversation table CRUD

    /**
     * Creates a new conversation record in the chat database if no conversation already exists
     * with these users.
     * Updates the conversation record if a conversation already exists with these users.
     * Called when a match is made.
     *
     * @param newConversation representation of the new conversation to be added,
     *                        or the state to which the existing conversation is to be modified`.
     * @return conversation ID of the newly-created/edited conversation if successful;
     *          null otherwise.
     */
    String addOrUpdateConversation(ConversationModel newConversation);

    /*
    /**
     * Creates a new conversation record in the chat database if no conversation already exists
     * with these users.
     * Updates the conversation record if a conversation already exists with these users.
     * Called when a match is made.
     *
     * @param conversationID ID of the conversation to be modified
     * @param newUsers list of userIDs of users in the chat.
     *                 replaces previous list of users.
     * @param newLastInteraction replaces the previous "lastInteraction" record
     * @param newTimeLastAction replaces the previous "time of lastInteraction" record
     * @return true if conversation creation was successful (conversation did not already exist);
     *          false otherwise.
     *\/
    abstract boolean addOrUpdateConversation(String conversationID, String[] newUsers,
                                                    String newLastInteraction,
                                                    Timestamp newTimeLastAction);
    Commented this out because I think it shouldn't be used.
    */

    /**
     * Returns a representation of a specified conversation.
     * @param conversationID ID of the desired conversation.
     * @return representation of the conversation matching this unique ID.
     *          null if no such conversation is found.
     */
    ConversationModel getConversation(String conversationID);

    /**
     * Returns a representation of a specified conversation.
     * @param users userIDs of the users in the conversation.
     * @return representation of the most recently-active conversation with these users.
     *          null if no such conversation is found.
     */
    ConversationModel getConversation(String[] users);

    /**
     * Returns a list representing all conversations that a user is currently in.
     * @param userID the userID of the user whose list of conversations you want to retrieve
     * @return a list representing all conversations that the user is in
     */
    ArrayList<ConversationModel> getUserConversationList(String userID);

    /**
     * @return list of all conversations in the database
     */
    ArrayList<ConversationModel> getAllConversations();

    /**
     * Deletes the record of a conversation.
     * @param conversationID conversationID ID of the to-be-deleted conversation.
     * @return true if successful; false otherwise (e.g. conversation not found).
     */
    boolean deleteConversation(String conversationID);

    /**
     * Deletes the record of a conversation using its list of users.
     * @param users userIDs of the members of the to-be-deleted conversation.
     * @return true if successful; false otherwise (e.g. conversation not found).
     */
    boolean deleteConversation(String[] users);

    /**
     * Deletes all conversation records. Does not delete messages records.
     * @return true if successful; false otherwise.
     */
    boolean deleteAllConversations();

    /**
     * Deletes all conversation and message records.
     * @return true if successful; false otherwise.
     */
    boolean deleteAllConversationChatRecords();
}
