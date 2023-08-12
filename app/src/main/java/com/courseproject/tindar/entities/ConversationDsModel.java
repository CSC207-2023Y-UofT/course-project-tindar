package com.courseproject.tindar.entities;

/**
 * Used for manipulating conversation records with ChatDatabaseGateways
 */
public interface ConversationDsModel {

    /**
     * Returns the ID of representation of a conversation
     * @return this conversation's ID
     */
    int getConversationId();

    /**
     * Returns the users in this conversation.
     * @return the IDs of the users involved in this conversation, in ascending numerical order
     */
    int[] getUsers();
}
