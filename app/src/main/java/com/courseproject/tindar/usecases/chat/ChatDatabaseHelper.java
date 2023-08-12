package com.courseproject.tindar.usecases.chat;

import java.util.Arrays;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.courseproject.tindar.entities.ConversationDsModel;
import com.courseproject.tindar.entities.MessageModel;
import com.courseproject.tindar.entities.TindarMessage;

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
     * ---------------------------------------------------------------------------------------------
     * JavaDoc from:
     * <a href="https://guides.codepath.com/android/local-databases-with-sqliteopenhelper#singleton-pattern">CodePath</a>
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
     * Javadocs from <a href="https://guides.codepath.com/android/local-databases-with-sqliteopenhelper">CodePath</a>
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MESSAGES_TABLE = "CREATE TABLE " + TABLE_MESSAGES +
                "(" +
                KEY_MESSAGE_ID + " INTEGER NOT NULL PRIMARY KEY," // Define a primary key
                // KEY_MESSAGE_SENDER_ID + " INTEGER REFERENCES " + TABLE_USERS + "," // Define a foreign key
                + KEY_MESSAGE_CREATION_TIME + " INTEGER, "
                + KEY_MESSAGE_CONTENT + " TEXT, "
                + KEY_MESSAGE_SENDER_ID + " INTEGER, "
                + KEY_MESSAGE_RECIPIENT_ID + " INTEGER"
                + ")";
        String CREATE_CONVERSATIONS_TABLE = "CREATE TABLE " + TABLE_CONVERSATIONS +
                "(" +
                KEY_CONVERSATION_ID + " INTEGER NOT NULL PRIMARY KEY," // Define a primary key
                + KEY_USER_1 + " INTEGER, "
                + KEY_USER_2 + " INTEGER"
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
        // TODO: this probably needs to be fixed
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGE_ID, newMessage.getMessageId());
        values.put(KEY_MESSAGE_CREATION_TIME, newMessage.getCreationTime().getTime());
        values.put(KEY_MESSAGE_CONTENT, newMessage.getMessageContent());
        values.put(KEY_MESSAGE_SENDER_ID, newMessage.getSentFromId());
        values.put(KEY_MESSAGE_RECIPIENT_ID, newMessage.getSentToId());

        db.insertOrThrow(TABLE_MESSAGES, null, values);
        return true;
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
     * @return a list representing all messages in the conversation with these users
     */
    @SuppressLint("Range")
    @Override
    public ArrayList<MessageModel> loadAllConversationMessages(String[] users) {
        int[] userIntIds = stringArrayToIntArray(users);
        Arrays.sort(userIntIds);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "
                        + KEY_MESSAGE_ID + ", "
                        + KEY_MESSAGE_CREATION_TIME + ", "
                        + KEY_MESSAGE_CONTENT + ", "
                        + KEY_MESSAGE_SENDER_ID + ", "
                        + KEY_MESSAGE_RECIPIENT_ID
                        + " FROM " + TABLE_MESSAGES
                        + " WHERE " + KEY_USER_1 + " =? AND " + KEY_USER_2 + " =?",
                new String[]{String.valueOf(userIntIds[0]), String.valueOf(userIntIds[1])});

        ArrayList<MessageModel> messageList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                messageList.add(new TindarMessage(
                        cursor.getString(cursor.getColumnIndex(KEY_MESSAGE_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_MESSAGE_CONTENT)),
                        new Timestamp(cursor.getLong(cursor.getColumnIndex(KEY_MESSAGE_CREATION_TIME))),
                        cursor.getString(cursor.getColumnIndex(KEY_MESSAGE_SENDER_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_MESSAGE_RECIPIENT_ID))));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return messageList;
    }

    /**
     * Deletes the record of a message using its ID.
     *
     * @param MessageId Message ID of the to-be-deleted message.
     * @return true if successful; false otherwise (e.g. message not found).
     */
    @Override
    public boolean deleteMessage(String MessageId) {
        return false;
    }

    /**
     * Deletes all message records. Does not delete conversation records.
     *
     * @return true if successful; false otherwise.
     */
    @Override
    public boolean deleteAllMessages() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_MESSAGES);
        // TODO: when would this fail?
        return true;
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
    public String addOrUpdateConversation(ConversationDsModel newConversation) {
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
    public ConversationDsModel getConversation(String conversationID) {
        return null;
    }

    /**
     * Returns a list representing all conversations that a user is currently in.
     *
     * @param userID the userID of the user whose list of conversations you want to retrieve
     * @return a list representing all conversations that the user is in
     */
    @Override
    public ArrayList<ConversationDsModel> getUserConversationList(String userID) {
        return null;
    }

    /**
     * @return list of all conversations in the database
     */
    @Override
    public ArrayList<ConversationDsModel> getAllConversations() {
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
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_CONVERSATIONS);
        // TODO: when would this fail?
        return true;
    }

    /**
     * Deletes all conversation and message records.
     *
     * @return true if successful; false otherwise.
     */
    @Override
    public boolean deleteAllConversationChatRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_MESSAGES);
        db.execSQL("delete from " + TABLE_CONVERSATIONS);
        // TODO: when would this fail?
        return true;
    }

    /**
     *
     * @return the inputted array, with each element cast to an integer
     */
    private int[] stringArrayToIntArray(String[] strArr){
        int size = strArr.length;
        int [] intArr = new int [size];
        for(int i=0; i<size; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }
}
