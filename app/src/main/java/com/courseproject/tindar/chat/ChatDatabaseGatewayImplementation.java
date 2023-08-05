package com.courseproject.tindar.chat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Sophia Wan
 */
public class ChatDatabaseGatewayImplementation extends SQLiteOpenHelper implements ChatDatabaseGateway {
    // Using the Singleton Pattern as outlined in:
    // https://guides.codepath.com/android/local-databases-with-sqliteopenhelper#singleton-pattern
    private static ChatDatabaseGatewayImplementation sInstance;

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
    private ChatDatabaseGatewayImplementation(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
     * Not called if a database already exists on disk with the same DATABASE_NAME
     * Javadocs from https://guides.codepath.com/android/local-databases-with-sqliteopenhelper
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MESSAGES_TABLE = "CREATE TABLE " + TABLE_MESSAGES +
                "(" +
                KEY_MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," // Define a primary key
                // KEY_MESSAGE_SENDER_ID + " INTEGER REFERENCES " + TABLE_USERS + "," // Define a foreign key
                + KEY_MESSAGE_CREATION_TIME + " TIMESTAMP, "
                + KEY_MESSAGE_CONTENT + " TEXT, "
                + KEY_MESSAGE_SENDER_ID + " INTEGER, "
                + KEY_MESSAGE_RECIPIENT_ID + " INTEGER"
                + ")";
        String CREATE_CONVERSATIONS_TABLE = "CREATE TABLE " + TABLE_MESSAGES +
                "(" +
                KEY_CONVERSATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," // Define a primary key
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
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
            onCreate(db);
        }
    }
}
