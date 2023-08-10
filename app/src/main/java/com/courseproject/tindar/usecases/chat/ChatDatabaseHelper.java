package com.courseproject.tindar.usecases.chat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author Sophia Wan
 */
public class ChatDatabaseHelper extends SQLiteOpenHelper implements ChatDatabaseGateway {
    // Using the Singleton Pattern as outlined in:
    // https://guides.codepath.com/android/local-databases-with-sqliteopenhelper#singleton-pattern
    private static ChatDatabaseHelper sInstance;

    // Database info
    private static final String DATABASE_NAME = "messagesDatabase";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_MESSAGES = "messages";
    private static final String TABLE_CONVERSATIONS = "conversations";

    // Message Table Columns
    private static final String KEY_MESSAGE_ID = "messageID";
    private static final String KEY_MESSAGE_CREATION_TIME = "messageCreationTime";
    private static final String KEY_MESSAGE_CONTENT = "messageContent";
    private static final String KEY_MESSAGE_SENDER_ID = "messageSentFromID";
    private static final String KEY_MESSAGE_RECIPIENT_ID = "messageSentToID";

    // Conversation Table Columns
    private static final String KEY_CONVERSATION_ID = "conversationID";
    private static final String KEY_USER_1 = "conversationUser1"; // alphabetically first user in conversation
    private static final String KEY_USER_2 = "conversationUser2"; // alphabetically last user in conversation
    private static final String KEY_CONVERSATION_LAST_INTERACTION
            = "conversationLastInteraction";
    private static final String KEY_CONVERSATION_LAST_INTERACTION_TIME
            = "conversationLastInteractionTime";

    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     *
     * JavaDoc from:
     * https://guides.codepath.com/android/local-databases-with-sqliteopenhelper#singleton-pattern
     */
    private ChatDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Copying from site; no idea how this works and contributes to the singleton pattern.
     * will figure it out later.
     * @param context
     * @return
     */
    public static synchronized ChatDatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new ChatDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Called when the database connection is being configured.
     * Configure database settings for things like foreign key support, write-ahead logging, etc.
     * Javadocs from https://guides.codepath.com/android/local-databases-with-sqliteopenhelper
     * @param db TODO
     */
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    /**
     * Called when the database is created for the FIRST time.
     * Not called if a database already exists on disk with the same DATABASE_NAME.
     * Creates empty message and conversation tables.
     * Javadocs from https://guides.codepath.com/android/local-databases-with-sqliteopenhelper
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MESSAGES_TABLE = "CREATE TABLE " + TABLE_MESSAGES +
                "(" +
                KEY_MESSAGE_ID + " INTEGER NOT NULL PRIMARY KEY," // Define a primary key
                // KEY_MESSAGE_SENDER_ID + " INTEGER REFERENCES " + TABLE_USERS + "," // Define a foreign key
                + KEY_MESSAGE_CREATION_TIME + " TIMESTAMP, "
                + KEY_MESSAGE_CONTENT + " TEXT, "
                + KEY_MESSAGE_SENDER_ID + " INTEGER, "
                + KEY_MESSAGE_RECIPIENT_ID + " INTEGER"
                + ")";
        String CREATE_CONVERSATIONS_TABLE = "CREATE TABLE " + TABLE_MESSAGES +
                "(" +
                KEY_CONVERSATION_ID + " INTEGER NOT NULL PRIMARY KEY," // Define a primary key
                + KEY_USER_1 + " INTEGER, "
                + KEY_USER_2 + " INTEGER, "
                + KEY_CONVERSATION_LAST_INTERACTION_TIME + " TIMESTAMP, "
                + KEY_CONVERSATION_LAST_INTERACTION + " TEXT"
                + ")";
        db.execSQL(CREATE_MESSAGES_TABLE);
        db.execSQL(CREATE_CONVERSATIONS_TABLE);
    }

    /**
     * Called when the database needs to be upgraded.
     * This method will only be called if a database already exists on disk with the same DATABASE_NAME,
     * but the DATABASE_VERSION is different than the version of the database that exists on disk.
     * @param db database to update
     * @param oldVersion old version number
     * @param newVersion new version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
            onCreate(db);
        }
    }

    /**
     * Creates a new message record in the chat database using the IDs of the users involved.
     * Requires the messageID to have already been created.
     *
     * @param newMessage representation of the new message that this method will record.
     * @return true if successful; false otherwise (e.g. message already exists)
     */
    @Override
    public boolean addMessage(MessageModel newMessage) {
        return false;
    }

    /**
     * @param messageID messageID of the desired message
     * @return representation of desired message if found; null otherwise
     */
    @Override
    public MessageModel getMessage(String messageID) {
        return null;
    }

    /**
     * Returns a list representing all messages in a given conversation
     *
     * @param users userIDs of users in this conversation
     * @return
     */
    @Override
    public ArrayList<MessageModel> loadAllConversationMessages(String[] users) {
        return null;
    }

    /**
     * Returns a list representing all messages in a given conversation
     *
     * @param conversationID conversationID of the desired conversation
     * @return list of all messages in a conversation, from oldest to newest if the conversation exists
     * (empty list if no message); null otherwise.
     */
    @Override
    public ArrayList<MessageModel> loadAllConversationMessages(String conversationID) {
        return null;
    }

    /**
     * Creates a new conversation record in the chat database if no conversation already exists
     * with these users.
     * Updates the conversation record if a conversation already exists with these users.
     * Called when a match is made.
     *
     * @param newConversation representation of the new conversation to be added,
     *                        or the state to which the existing conversation is to be modified`.
     * @return conversation ID of the newly-created/edited conversation if successful;
     * null otherwise.
     */
    @Override
    public String addOrUpdateConversation(ConversationModel newConversation) {
        return null;
    }

    /**
     * Updates the conversation record if a conversation already exists with this conversationID.
     *
     * @param conversationID     ID of the conversation to be modified
     * @param newLastInteraction replaces the previous "lastInteraction" record
     * @param newTimeLastAction  replaces the previous "time of lastInteraction" record
     * @return true if conversation exists and was successfully updated;
     * false otherwise.
     */
    @Override
    public String updateConversation(String conversationID, String newLastInteraction, Timestamp newTimeLastAction) {
        return null;
    }

    /**
     * Returns a representation of a specified conversation.
     *
     * @param conversationID ID of the desired conversation.
     * @return representation of the conversation matching this unique ID.
     * null if no such conversation is found.
     */
    @Override
    public ConversationModel getConversation(String conversationID) {
        return null;
    }

    /**
     * Returns a representation of a specified conversation.
     *
     * @param users userIDs of the users in the conversation.
     * @return representation of the most recently-active conversation with these users.
     * null if no such conversation is found.
     */
    @Override
    public ConversationModel getConversation(String[] users) {
        return null;
    }

    /**
     * Returns a list representing all conversations that a user is currently in.
     *
     * @param userID the userID of the user whose list of conversations you want to retrieve
     * @return a list representing all conversations that the user is in
     */
    @Override
    public ArrayList<ConversationModel> getUserConversationList(String userID) {
        return null;
    }

    /**
     * @return list of all conversations in the database
     */
    @Override
    public ArrayList<ConversationModel> getAllConversations() {
        return null;
    }

    /**
     * Deletes the record of a conversation.
     *
     * @param conversationID conversationID ID of the to-be-deleted conversation.
     * @return true if successful; false otherwise (e.g. conversation not found).
     */
    @Override
    public boolean deleteConversation(String conversationID) {
        return false;
    }

    /**
     * Deletes the record of a conversation using its list of users.
     *
     * @param users userIDs of the members of the to-be-deleted conversation.
     * @return true if successful; false otherwise (e.g. conversation not found).
     */
    @Override
    public boolean deleteConversation(String[] users) {
        return false;
    }

    /**
     * Deletes all conversation records. Does not delete messages records.
     *
     * @return true if successful; false otherwise.
     */
    @Override
    public boolean deleteAllConversations() {
        return false;
    }

    /**
     * Deletes all conversation and message records.
     *
     * @return true if successful; false otherwise.
     */
    @Override
    public boolean deleteAllConversationChatRecords() {
        return false;
    }
}
