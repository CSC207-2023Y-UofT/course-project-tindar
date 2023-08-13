package com.courseproject.tindar.ds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.courseproject.tindar.entities.MessageModel;
import com.courseproject.tindar.entities.TindarMessage;
import com.courseproject.tindar.usecases.chat.ChatDsGateway;
import com.courseproject.tindar.usecases.chat.ChatRequestModel;
import com.courseproject.tindar.usecases.conversationlist.ConversationListDsGateway;
import com.courseproject.tindar.usecases.conversationlist.ConversationMessageDsResponseModel;
import com.courseproject.tindar.usecases.editaccount.EditAccountDsGateway;
import com.courseproject.tindar.usecases.editaccount.EditAccountDsResponseModel;
import com.courseproject.tindar.usecases.editfilters.EditFiltersDsGateway;
import com.courseproject.tindar.usecases.editfilters.EditFiltersModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsGateway;
import com.courseproject.tindar.usecases.editprofile.EditProfileRequestModel;
import com.courseproject.tindar.usecases.login.LoginDsGateway;
import com.courseproject.tindar.usecases.likelist.LikeListDsGateway;
import com.courseproject.tindar.usecases.matchlist.MatchListDsGateway;
import com.courseproject.tindar.usecases.matchlist.MatchListDsResponseModel;
import com.courseproject.tindar.usecases.userlist.UserListDsGateway;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileDsGateway;
import com.courseproject.tindar.usecases.viewprofile.ViewProfileResponseModel;
import com.courseproject.tindar.usecases.signup.SignUpDsGateway;
import com.courseproject.tindar.usecases.signup.SignUpDsRequestModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * Database Helper which implements database methods for Edit Profile, Edit Filters, Login, Sign Up, Like List, View
 * Profile, User List, Edit Account features. This also implements method on creating database, on upgrading
 * database, and to get database instance.
 */
public class DatabaseHelper extends SQLiteOpenHelper implements EditProfileDsGateway, EditFiltersDsGateway,
        LoginDsGateway, SignUpDsGateway, LikeListDsGateway, ViewProfileDsGateway, UserListDsGateway,
        MatchListDsGateway, EditAccountDsGateway, ConversationListDsGateway,ChatDsGateway {
    /**
     * app database instance
     */
    private static DatabaseHelper dbInstance;
    /**
     * test database instance
     */
    private static DatabaseHelper testDbInstance;
    /**
     * table name for the accounts
     */
    private static final String TABLE_ACCOUNTS = "accounts";
    /**
     * table name for the likes
     */
    private static final String TABLE_LIKES = "likes";
    /**
     * table name for the matches
     */
    private static final String TABLE_MATCHES = "matches";
    private static final String TABLE_CONVERSATIONS = "conversations";
    private static final String TABLE_MESSAGES = "messages";
    /**
     * column name for the id
     */
    private static final String ID = "id";
    /**
     * column name for the is active status
     */
    private static final String IS_ACTIVE_STATUS = "is_active_status";
    /**
     * column name for the email
     */
    private static final String EMAIL = "is_email";
    /**
     * column name for the password
     */
    private static final String PASSWORD = "password";
    /**
     * column name for the display name
     */
    private static final String DISPLAY_NAME = "display_name";
    /**
     * column name for the first name
     */
    private static final String FIRST_NAME = "first_name";
    /**
     * column name for the last name
     */
    private static final String LAST_NAME = "last_name";
    /**
     * column name for the birthdate
     */
    private static final String BIRTHDATE = "birthdate";
    /**
     * column name for the gender
     */
    private static final String GENDER = "gender";
    /**
     * column name for the location
     */
    private static final String LOCATION = "location";
    /**
     * column name for the link to the profile picture
     */
    private static final String PROFILE_PICTURE_LINK = "profile_picture_link";
    /**
     * column name for the about me statement
     */
    private static final String ABOUT_ME = "about_me";
    /**
     * column name for the user's preferred genders for potential match
     */
    private static final String PREFERRED_GENDERS = "preferred_genders";
    /**
     * column name for the user's preferred locations for the potential match
     */
    private static final String PREFERRED_LOCATIONS = "preferred_locations";
    /**
     * column name for the minimum age of the user's preferred age group for the potential match
     */
    private static final String PREFERRED_AGE_MINIMUM = "preferred_age_minimum";
    /**
     * column name for the maximum age of the user's preferred age group for the potential match
     */
    private static final String PREFERRED_AGE_MAXIMUM = "preferred_age_maximum";
    /**
     * column name for the user id
     */
    private static final String USER_ID = "user_id";
    /**
     * column name for the user id, who is liked by another user
     */
    private static final String LIKED_USER_ID = "liked_user_id";
    /**
     * column name for the 1st user id in the group
     */
    private static final String USER_ID_1 = "user_id_1";
    /**
     * column name for the 2nd user id in the group
     */
    private static final String USER_ID_2 = "user_id_2";
    private static final String CREATION_TIME = "creation_time";
    private static final String CONTENT = "content";
    private static final String SENDER_ID = "sender_user_id";
    private static final String RECIPIENT_ID = "recipient_user_id";
    private static final String CONVERSATION_ID = "conversation_id";

    /**
     * sql query to create accounts table. There is unique constraint for the email column
     */
    private static final String CREATE_TABLE_ACCOUNTS_QUERY = "CREATE TABLE " + TABLE_ACCOUNTS + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + IS_ACTIVE_STATUS + " NUMBER(1) NOT NULL, "
            + EMAIL + " VARCHAR(30) NOT NULL UNIQUE, "
            + PASSWORD + " VARCHAR(30) NOT NULL, "
            + DISPLAY_NAME + " VARCHAR(30), "
            + FIRST_NAME + " VARCHAR(30), "
            + LAST_NAME + " VARCHAR(30), "
            + BIRTHDATE + " DATE, "
            + GENDER + " VARCHAR(30), "
            + LOCATION + " VARCHAR(30), "
            + PROFILE_PICTURE_LINK + " TEXT, "
            + ABOUT_ME + " TEXT, "
            + PREFERRED_GENDERS + " TEXT NOT NULL, "
            + PREFERRED_LOCATIONS + " TEXT NOT NULL, "
            + PREFERRED_AGE_MINIMUM + " TEXT NOT NULL, "
            + PREFERRED_AGE_MAXIMUM + " TEXT NOT NULL);";

    /**
     * sql query to create likes table. There is unique constraint in the combination of user id and liked user id
     */
    private static final String CREATE_TABLE_LIKES_QUERY = "CREATE TABLE " + TABLE_LIKES + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INTEGER, "
            + LIKED_USER_ID + " INTEGER, "
            + "FOREIGN KEY (" + USER_ID + ") REFERENCES " + TABLE_ACCOUNTS + "(" + ID + "), "
            + "FOREIGN KEY (" + LIKED_USER_ID + ") REFERENCES " + TABLE_ACCOUNTS + "(" + ID + "), "
            + "UNIQUE(" + USER_ID + ", " + LIKED_USER_ID + "));";

    /**
     * sql query to create matches table. There is unique constraint in the combination of 1st user id and 2nd user
     * id in the group
     */
    private static final String CREATE_TABLE_MATCHES_QUERY = "CREATE TABLE " + TABLE_MATCHES + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_ID_1 + " INTEGER, "
            + USER_ID_2 + " INTEGER, "
            + "FOREIGN KEY (" + USER_ID_1 + ") REFERENCES " + TABLE_ACCOUNTS + "(" + ID + "), "
            + "FOREIGN KEY (" + USER_ID_2 + ") REFERENCES " + TABLE_ACCOUNTS + "(" + ID + "), "
            + "UNIQUE(" + USER_ID_1 + ", " + USER_ID_2 + "));";

    private static final String CREATE_TABLE_CONVERSATIONS_QUERY = "CREATE TABLE " + TABLE_CONVERSATIONS + "("
            + ID + " INTEGER NOT NULL PRIMARY KEY," // Define a primary key
            + USER_ID_1 + " INTEGER, "
            + USER_ID_2 + " INTEGER, "
            + "FOREIGN KEY (" + USER_ID_1 + ") REFERENCES " + TABLE_ACCOUNTS + "(" + ID + "), "
            + "FOREIGN KEY (" + USER_ID_2 + ") REFERENCES " + TABLE_ACCOUNTS + "(" + ID + "), "
            + "UNIQUE(" + USER_ID_1 + ", " + USER_ID_2 + "));";

    private static final String CREATE_TABLE_MESSAGES_QUERY = "CREATE TABLE " + TABLE_MESSAGES + " ("
            + ID + " INTEGER NOT NULL PRIMARY KEY," // Define a primary key
            + CREATION_TIME + " INTEGER, "
            + CONTENT + " TEXT, "
            + SENDER_ID + " INTEGER, "
            + RECIPIENT_ID + " INTEGER, "
            + CONVERSATION_ID + " INTEGER, "
            + "FOREIGN KEY (" + CONVERSATION_ID + ") REFERENCES " + TABLE_CONVERSATIONS + "(" + ID + "));";

    /**
     * returns DatabaseHelper instance. If the instance already exists it returns exiting instance, if not, it creates
     * a instance and returns it. This insures there is only one instance of DatabaseHelper for the application.
     *
     * @param context an activity's context
     * @return an instance of DatabaseHelper
     **/
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (dbInstance == null) {
            // Use the application context
            dbInstance = new DatabaseHelper(context.getApplicationContext(), "tindar.db");
        }
        return dbInstance;
    }

    /**
     * returns test DatabaseHelper instance. If the instance already exists it returns exiting instance, if not, it
     * creates a instance and returns it. This insures there is only one instance of test DatabaseHelper for the
     * application.
     *
     * @param context an activity's context
     * @return an instance of the test DatabaseHelper
     **/
    public static synchronized DatabaseHelper getTestInstance(Context context) {
        if (testDbInstance == null) {
            // Use the application context
            testDbInstance = new DatabaseHelper(context.getApplicationContext(), "test.db");
        }
        return testDbInstance;
    }

    /**
     * constructs database helper
     *
     * @param context current context of the application where the database is constructed
     * @param databaseName name of the database
     */
    // access modifier is private so DatabaseHelper doesn't get directly instantiated. The instantiation of
    // DatabaseHelper should go through getInstance method.
    private DatabaseHelper(@Nullable Context context, String databaseName) {
        super(context, databaseName, null, 1);
    }

    /**
     * gets called when the database is being created. This creates tables and adds initial dummy data to the
     * database
     *
     * @param db the database instance
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ACCOUNTS_QUERY);
        db.execSQL(CREATE_TABLE_LIKES_QUERY);
        db.execSQL(CREATE_TABLE_MATCHES_QUERY);
        db.execSQL(CREATE_TABLE_MESSAGES_QUERY);
        db.execSQL(CREATE_TABLE_CONVERSATIONS_QUERY);
        addInitialData(db);
    }

    /**
     * gets called when there is change in the version of the database. This drops all existing tables and calls
     * onCreate method to re-create the tables with the initial dummy data.
     *
     * @param db the database instance
     * @param oldVersion old version number
     * @param newVersion new version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIKES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATCHES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONVERSATIONS);
        onCreate(db);
    }

    /**
     * cleans out all records in the database. This is for the testing purpose so each test can be run on
     * the database with no record to avoid unintended noise in the testing
     */
    public void deleteAllDbRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_ACCOUNTS);
        db.execSQL("delete from " + TABLE_LIKES);
        db.execSQL("delete from " + TABLE_MATCHES);
        db.execSQL("delete from " + TABLE_MESSAGES);
        db.execSQL("delete from " + TABLE_CONVERSATIONS);
    }

    /**
     * Adds dummy data to the database. This is for a demo purpose of the app.
     *
     * @param db the database instance
     **/
    private void addInitialData(SQLiteDatabase db) {
        addAccount(true, "jack@someemail.com", "password_jack", "jack",
                "Jack", "Brown", new GregorianCalendar(2000, 1, 26).getTime(),
                "Male", "Toronto", "https://www.cartoonbucket.com/cartoons/stanley-with-spongebob/", "Hi", "Female, Other",
                "Toronto", 20, 25, db);
        addAccount(true, "amy@someotheremail.com", "password_amy", "amy",
                "Amy", "Smith", new GregorianCalendar(2000, 7, 2).getTime(),
                "Female", "Montreal", "https://www.cartoonbucket.com/cartoons/stanley-with-spongebob/", "Hello","Male",
                "Montreal, Toronto", 23, 27, db);
        addAccount(true, "bell@exampleemail.com", "somepassword", "bell",
                "Bell", "Robin", new GregorianCalendar(2003, 9, 5).getTime(),
                "Female", "Calgary", "https://www.cartoonbucket.com/cartoons/stanley-with-spongebob/", "I would like to",
                "Female, Male", "Calgary, Vancouver", 19, 999, db);
        addAccount(true, "rogers@exampleemail.com", "someotherpassword", "roger",
                "roger", "fido", new GregorianCalendar(2003, 12, 3).getTime(),
                "Female", "Calgary", "https://www.cartoonbucket.com/cartoons/stanley-with-spongebob/", "I would like to",
                "Female, Male", "Calgary, Vancouver", 19, 999, db);
        addAccount(true, "telus@exampleemail.com", "somethirdpassword", "ted",
                "ted", "telus", new GregorianCalendar(2001, 12, 3).getTime(),
                "Male", "Toronto", "https://www.cartoonbucket.com/cartoons/stanley-with-spongebob/", "I would like to",
                "Female, Male", "Calgary, Vancouver", 19, 999, db);
        addLike("1", "2", db);
        addLike("2", "1", db);
        addLike("1", "5", db);
        addLike("5", "1", db);
        addToMatched("1", "2", db);
        addToMatched("1", "5", db);
    }

    /**
     * adds account to the account table for the database base instance passed as an argument
     *
     * @param isActiveStatus indicates whether the account is in active status or not
     * @param email email address of the user
     * @param password password of the account the user creates
     * @param displayName display name of the user
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @param birthdate birthdate of the user
     * @param gender gender of the user
     * @param location location where the user lives in
     * @param profilePictureLink link to the profile picture of the user
     * @param aboutMe statement the user writes to introduce him/herself to other users
     * @param preferredGenders list of user's preferred genders for the potential match
     * @param preferredLocations list of user's preferred locations for the potential match
     * @param preferredAgeMinimum minimum age of user's preferred age group for the potential match
     * @param preferredAgeMaximum maximum age of user's preferred age group for the potential match
     * @param db the database instance
     * @return id of the user whose account is newly created
     */
    private String addAccount(boolean isActiveStatus, String email, String password, String displayName,
                              String firstName, String lastName, java.util.Date birthdate, String gender, String location,
                              String profilePictureLink, String aboutMe, String preferredGenders,
                              String preferredLocations, int preferredAgeMinimum,
                              int preferredAgeMaximum, SQLiteDatabase db) {

        ContentValues cv = new ContentValues();
        cv.put(IS_ACTIVE_STATUS, isActiveStatus ? 1 : 0);
        cv.put(EMAIL, email);
        cv.put(PASSWORD, password);
        cv.put(DISPLAY_NAME, displayName);
        cv.put(FIRST_NAME, firstName);
        cv.put(LAST_NAME, lastName);
        if (birthdate != null) {
            cv.put(BIRTHDATE, new java.sql.Date(birthdate.getTime()).getTime());
        }
        cv.put(GENDER, gender);
        cv.put(LOCATION, location);
        cv.put(PROFILE_PICTURE_LINK, profilePictureLink);
        cv.put(ABOUT_ME, aboutMe);
        cv.put(PREFERRED_GENDERS, preferredGenders);
        cv.put(PREFERRED_LOCATIONS, preferredLocations);
        cv.put(PREFERRED_AGE_MINIMUM, preferredAgeMinimum);
        cv.put(PREFERRED_AGE_MAXIMUM, preferredAgeMaximum);

        long insertedId = db.insert(TABLE_ACCOUNTS, null, cv);
        return String.valueOf(insertedId);
    }

    /**
     * adds account to the account table. The database instance is obtained within the method.
     *
     * @param isActiveStatus indicates whether the account is in active status or not
     * @param email email address of the user
     * @param password password of the account the user creates
     * @param displayName display name of the user
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @param birthdate birthdate of the user
     * @param gender gender of the user
     * @param location location where the user lives in
     * @param profilePictureLink link to the profile picture of the user
     * @param aboutMe statement the user writes to introduce him/herself to other users
     * @param preferredGenders list of user's preferred genders for the potential match
     * @param preferredLocations list of user's preferred locations for the potential match
     * @param preferredAgeMinimum minimum age of user's preferred age group for the potential match
     * @param preferredAgeMaximum maximum age of user's preferred age group for the potential match
     * @return id of the user whose account is newly created
     */
    public String addAccount(boolean isActiveStatus, String email, String password, String displayName, String firstName,
                             String lastName, java.util.Date birthdate, String gender, String location,
                             String profilePictureLink, String aboutMe, String preferredGenders,
                             String preferredLocations, int preferredAgeMinimum, int preferredAgeMaximum) {

        SQLiteDatabase db = this.getReadableDatabase();

        return addAccount(isActiveStatus, email, password, displayName, firstName, lastName, birthdate, gender, location,
                profilePictureLink, aboutMe, preferredGenders, preferredLocations, preferredAgeMinimum,
                preferredAgeMaximum, db);
    }

    /**
     * adds account to the account table with the sign-up credentials
     *
     * @param signUpDsRequestModel sign-up credentials provided
     * @return id of the user whose account is newly created
     */
    @Override
    public String addAccount(SignUpDsRequestModel signUpDsRequestModel) {
        return addAccount(true, signUpDsRequestModel.getEmail(), signUpDsRequestModel.getPassword(),
                signUpDsRequestModel.getDisplayName(), "", "", null, "", "",
                "", "", "", "", 19, 999);
    }

    /**
     * checks whether email address is already used by another user in the database
     *
     * @param email email address of the user
     * @return true if email is already used; false otherwise
     */
    @Override
    public boolean checkIfEmailAlreadyUsed(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "
                        + EMAIL
                        + " FROM " + TABLE_ACCOUNTS
                        + " WHERE " + EMAIL + " =?",
                new String[]{email});

        boolean isEmailAlreadyUsed = cursor.getCount() > 0;

        cursor.close();
        return isEmailAlreadyUsed;
    }

    /** Retrieves information about an account from the database.
     *
     * @param userId the user id of the account
     * @return the active status, email and password of the account
     */
    @Override
    public EditAccountDsResponseModel readAccount(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "
                + IS_ACTIVE_STATUS + ", "
                + EMAIL + ", "
                + PASSWORD
                + " FROM " + TABLE_ACCOUNTS
                + " WHERE " + ID + " =?",
            new String[]{userId});

        cursor.moveToFirst();

        EditAccountDsResponseModel dsResponse = new EditAccountDsResponseModel(
            cursor.getInt(0) > 0,
            cursor.getString(1),
            cursor.getString(2)
        );

        cursor.close();
        return dsResponse;
    }

    /** Updates the active status of an account in the database.
     *
     * @param userId the user id of the account
     * @param isActiveStatus the status the account will be changed to
     */
    @Override
    public void updateIsActiveStatus(String userId, boolean isActiveStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(IS_ACTIVE_STATUS, isActiveStatus);

        db.update(TABLE_ACCOUNTS, cv, ID + "=?", new String[]{userId});
        db.close();
    }

    /** Updates the email associated with an account in the database.
     * Returns false if the email is already used by an account.
     *
     * @param userId the user id of the account
     * @param email the new email to be associated with the account
     * @return true if the email was successfully updated
     */
    @Override
    public boolean updateEmail(String userId, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(EMAIL, email);
        try {
            db.update(TABLE_ACCOUNTS, cv, ID + "=?", new String[]{userId});
        }
        catch (SQLiteConstraintException e){
            db.close();
            return false;
        }
        db.close();
        return true;
    }

    /** Updates the password associated with an account.
     *
     * @param userId the user id of the account
     * @param password the new password to be associated with the account
     * @return true if the password was successfully updated
     */
    @Override
    public boolean updatePassword(String userId, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PASSWORD, password);

        db.update(TABLE_ACCOUNTS, cv, ID + "=?", new String[]{userId});
        db.close();
        return true;
    }

    /** Retrieves profile information of a user from the database. It includes birthdate, gender, location,
     * profile picture link, and about me statement of the user.
     *
     * @param userId the user id of the account
     * @return profile information of the user
     */
    @Override
    public ViewProfileResponseModel readProfile(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "
                + DISPLAY_NAME + ", "
                + BIRTHDATE + ", "
                + GENDER + ", "
                + LOCATION + ", "
                + PROFILE_PICTURE_LINK + ", "
                + ABOUT_ME
                + " FROM " + TABLE_ACCOUNTS
                + " WHERE " + ID + " =?",
            new String[]{userId});

        cursor.moveToFirst();

        ViewProfileResponseModel dsResponse = new ViewProfileResponseModel(
            cursor.getString(0),
            new java.util.Date(cursor.getLong(1)),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getString(4),
            cursor.getString(5)
        );

        cursor.close();
        return dsResponse;
    }

    /** updates the profile information of a user in the database. It includes birthdate, gender, location,
     * profile picture link, and about me statement of the user.
     *
     * @param userId the user id of the account
     * @param newProfile new profile information of the user
     */
    @Override
    public void updateProfile(String userId, EditProfileRequestModel newProfile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BIRTHDATE, new java.sql.Date(newProfile.getBirthdate().getTime()).getTime());
        cv.put(GENDER, newProfile.getGender());
        cv.put(LOCATION, newProfile.getLocation());
        cv.put(PROFILE_PICTURE_LINK, newProfile.getProfilePictureLink());
        cv.put(ABOUT_ME, newProfile.getAboutMe());

        db.update(TABLE_ACCOUNTS, cv, ID + "=?", new String[]{userId});
        db.close();
    }

    /**
     * retrieves filters information of the user
     *
     * @param userId the user id of the account
     * @return filters information of the user
     */
    @Override
    public EditFiltersModel readFilters(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "
                        + PREFERRED_GENDERS + ", "
                        + PREFERRED_LOCATIONS + ", "
                        + PREFERRED_AGE_MINIMUM + ", "
                        + PREFERRED_AGE_MAXIMUM
                        + " FROM " + TABLE_ACCOUNTS
                        + " WHERE " + ID + " =?",
                new String[]{userId});

        cursor.moveToFirst();

        // converts comma separated string to an ArrayList. If string is an empty string,
        // it creates an empty ArrayList.
        ArrayList<String> preferredGenders = new ArrayList<>(Arrays.asList(cursor.getString(0).split(", ")));
        ArrayList<String> preferredLocations = new ArrayList<>(Arrays.asList(cursor.getString(1).split(", ")));
        preferredGenders.removeIf(String::isEmpty);
        preferredLocations.removeIf(String::isEmpty);

        EditFiltersModel dsResponse = new EditFiltersModel(
                preferredGenders,
                preferredLocations,
                cursor.getInt(2),
                cursor.getInt(3)
        );

        cursor.close();
        return dsResponse;
    }

    /**
     * updates filters information of the user
     *
     * @param userId the user id of the account
     * @param newFilters new filters information of the user to be updated
     */
    @Override
    public void updateFilters(String userId, EditFiltersModel newFilters) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PREFERRED_GENDERS, String.join(", ", newFilters.getPreferredGenders()));
        cv.put(PREFERRED_LOCATIONS, String.join(", ", newFilters.getPreferredLocations()));
        cv.put(PREFERRED_AGE_MINIMUM, newFilters.getPreferredAgeMinimum());
        cv.put(PREFERRED_AGE_MAXIMUM, newFilters.getPreferredAgeMaximum());

        db.update(TABLE_ACCOUNTS, cv, ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public String readUserId(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "
                        + ID
                        + " FROM " + TABLE_ACCOUNTS
                        + " WHERE " + EMAIL + " =? AND " + PASSWORD + " =?",
                new String[]{email, password});

        if (cursor.getCount() == 0){
            cursor.close();
            return null;
        }

        cursor.moveToFirst();
        String userId = cursor.getString(0);
        cursor.close();

        return userId;
    }

    /** Check if either userId or other have liked each other
     * @param userId user who initiated a 'like' interaction
     * @param otherUserId user receiving a 'like' from userId
     * @return return true if users 'like' each other, false otherwise
     */
    public boolean checkLiked(String userId, String otherUserId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "
                        + ID
                        + " FROM " + TABLE_LIKES
                        + " WHERE " + USER_ID + " =? AND " + LIKED_USER_ID + " =?",
                new String[]{userId, otherUserId});

        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }

        cursor.close();
        return false;
    }

    /** Add userId and otherUserId to match list after a match has occurred
     * @param userId user who initiated 'like' interaction
     * @param otherUserId user receiving a 'like from userId
     * @param db database instance for storage of data
     */
    public void addToMatched(String userId, String otherUserId, SQLiteDatabase db) {
        // precondition: userId < otherUserId. This is to avoid duplicates of
        // (userId, otherUserId) and (otherUserId, userId)
        ContentValues cv = new ContentValues();
        cv.put(USER_ID_1, userId);
        cv.put(USER_ID_2, otherUserId);

        db.insert(TABLE_MATCHES, null, cv);
    }

    /** Calls addToMatched with userId values from LikeListInteractor
     * @param userId user who initiated 'like' interaction
     * @param otherUserId user receiving a 'like from userId
     */
    @Override
    public void addToMatched(String userId, String otherUserId) {
        SQLiteDatabase db = this.getWritableDatabase();
        addToMatched(userId, otherUserId, db);
    }

    /** Adds otherUserId and userId like each other to database
     * @param userId user who initiated 'like' interaction
     * @param otherUserId user receiving a 'like from userId
     * @param db database instance for storage of data
     */
    public void addLike(String userId, String otherUserId, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(USER_ID, userId);
        cv.put(LIKED_USER_ID, otherUserId);

        db.insert(TABLE_LIKES, null, cv);
    }

    /** Calls addLike above with userId values from LikeListInteractor
     * @param userId user who initiated 'like' interaction
     * @param otherUserId user receiving a 'like from userId
     */
    @Override
    public void addLike(String userId, String otherUserId) {
        SQLiteDatabase db = this.getWritableDatabase();
        addLike(userId, otherUserId, db);
    }

    /** Removes userId and otherUserId from like list in database
     * @param userId user who initiated 'like' interaction
     * @param otherUserId user who received a 'like from userId
     */
    @Override
    public void removeLike(String userId, String otherUserId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_ID, userId);
        cv.put(LIKED_USER_ID, otherUserId);

        db.delete(TABLE_LIKES, USER_ID + "= ? and " + LIKED_USER_ID + " = ?",
                new String[]{userId, otherUserId});
    }

    /** Removes userId and otherUserId from database match list
     * @param userId user who initiated 'like' interaction
     * @param otherUserId user who received a 'like from userId
     */
    @Override
    public void removeFromMatched(String userId, String otherUserId) {
        // precondition: userId < otherUserId. Since matches should not have duplicate of
        // (userId, otherUserId) and (otherUserId, userId), we should respect the order when
        // adding/deleting records of userId pair.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_ID_1, userId);
        cv.put(USER_ID_2, otherUserId);

        db.delete(TABLE_MATCHES, USER_ID_1 + "= ? and " + USER_ID_2 + " = ?",
                new String[]{userId, otherUserId});
    }

    /** Reads match list from database and returns ArrayList<String[]> of userIds
     * @param userId user who's match list we are retrieving
     * @return return list of userIds from match list
     */
    @Override
    public ArrayList<String[]> readMatchList(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "
                        + USER_ID_1 + ", "
                        + USER_ID_2
                        + " FROM " + TABLE_MATCHES
                        + " WHERE " + USER_ID_1 + " =? OR " + USER_ID_2 + " =?",
                new String[]{userId, userId});

        ArrayList<String[]> matchListResponse = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                matchListResponse.add(new String[]{
                        cursor.getString(0),
                        cursor.getString(1)});
            } while (cursor.moveToNext());
        }

        cursor.close();
        return matchListResponse;
    }

    /** Returns a list of Match list that is used to allow display names to be
     * shown on screen when plugged into MatchListFragment. Essentially returning a list of
     * display names
     * @param userIds users who's display names we are retrieving
     * @return return list of user display names
     */
    @Override
    public ArrayList<MatchListDsResponseModel> readDisplayNames(ArrayList<String> userIds) {
        SQLiteDatabase db = this.getReadableDatabase();

        boolean doNotAddComma = true;
        StringBuilder userIdsString = new StringBuilder("(");

        for(String userId : userIds){
            if(doNotAddComma){
                doNotAddComma = false;
            } else {
                userIdsString.append(",");
            }
            userIdsString.append("'").append(userId).append("'");
        }

        userIdsString.append(")");

        Cursor cursor = db.rawQuery("SELECT "
                        + ID + ", "
                        + DISPLAY_NAME
                        + " FROM " + TABLE_ACCOUNTS
                        + " WHERE " + ID + " IN " + userIdsString, null);

        ArrayList<MatchListDsResponseModel> displayNamesResponse = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                displayNamesResponse.add(new MatchListDsResponseModel(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return displayNamesResponse;
    }

    @Override
    public ArrayList<String> getAllOtherUserIds(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "
                + ID
                + " FROM " + TABLE_ACCOUNTS
                + " WHERE " + ID + " !=?",
                new String[]{userId});

        ArrayList<String> userIdsResponse = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                userIdsResponse.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return userIdsResponse;
    }

    // precondition: userId < otherUserId. This is to avoid duplicates of
    // (userId, otherUserId) and (otherUserId, userId)
    public void addConversation(String userId, String otherUserId, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(USER_ID_1, userId);
        cv.put(USER_ID_2, otherUserId);

        db.insert(TABLE_CONVERSATIONS, null, cv);
    }

    // precondition: userId < otherUserId. This is to avoid duplicates of
    // (userId, otherUserId) and (otherUserId, userId)
    @Override
    public void addConversation(String userId, String otherUserId) {
        SQLiteDatabase db = this.getWritableDatabase();
        addConversation(userId, otherUserId, db);
    }

    @Override
    public ArrayList<String[]> readConversationList(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "
                        + USER_ID_1 + ", "
                        + USER_ID_2
                        + " FROM " + TABLE_CONVERSATIONS
                        + " WHERE " + USER_ID_1 + " =? OR " + USER_ID_2 + " =?",
                new String[]{userId, userId});

        ArrayList<String[]> matchListResponse = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                matchListResponse.add(new String[]{
                        cursor.getString(0),
                        cursor.getString(1)});
            } while (cursor.moveToNext());
        }

        cursor.close();
        return matchListResponse;
    }

    @Override
    public ArrayList<String> readDisplayNamesForConversations(ArrayList<String> userIds) {
        SQLiteDatabase db = this.getReadableDatabase();

        boolean doNotAddComma = true;
        StringBuilder userIdsString = new StringBuilder("(");

        for(String userId : userIds){
            if(doNotAddComma){
                doNotAddComma = false;
            } else {
                userIdsString.append(",");
            }
            userIdsString.append("'").append(userId).append("'");
        }

        userIdsString.append(")");

        Cursor cursor = db.rawQuery("SELECT "
                + DISPLAY_NAME
                + " FROM " + TABLE_ACCOUNTS
                + " WHERE " + ID + " IN " + userIdsString, null);

        ArrayList<String> dbResponse = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                dbResponse.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return dbResponse;
    }

    @Override
    public ConversationMessageDsResponseModel readLastMessage(String conversationId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "
                        + CONTENT + ", "
                        + CREATION_TIME
                        + " FROM " + TABLE_MESSAGES
                        + " WHERE " + CONVERSATION_ID + " =?"
                        + " ORDER BY " + CREATION_TIME + " DESC",
                new String[]{conversationId});

        if (cursor.getCount() < 1) {
            return null;
        }

        cursor.moveToFirst();
        ConversationMessageDsResponseModel dbResponse = new ConversationMessageDsResponseModel(
                cursor.getString(0),
                new Timestamp(cursor.getLong(1)));

        cursor.close();
        return dbResponse;
    }

    // precondition userId < otherUserId
    @Override
    public String findConversationId(String userId, String otherUserId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "
                        + ID
                        + " FROM " + TABLE_CONVERSATIONS
                        + " WHERE " + USER_ID_1 + " =? OR " + USER_ID_2 + " =?",
                new String[]{userId, otherUserId});

        if (cursor.getCount() < 1) {
            cursor.close();
            return null;
        }

        cursor.moveToFirst();
        String dbResponse = cursor.getString(0);

        cursor.close();
        return dbResponse;
    }

    /**
     * Creates a new message record in the chat database.
     * Requires the messageId to have already been created.
     * In other words, the MessageModel's messageId is non-null and unique.
     *
     * @param newMessage representation of the new message that this method will record.
     */
    @Override
    public void addMessage(ChatRequestModel newMessage) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CREATION_TIME, newMessage.getCreationTime().getTime());
        values.put(CONTENT, newMessage.getText());
        values.put(SENDER_ID, newMessage.getSentFromId());
        values.put(RECIPIENT_ID, newMessage.getSentToId());
        values.put(CONVERSATION_ID, newMessage.getConversationId());

        db.insert(TABLE_MESSAGES, null, values);
    }

    /**
     * Returns a list representing all messages in a given conversation
     *
     * @param conversationId id of the conversation
     * @return a list representing all messages in the conversation with these users
     */
    @Override
    public ArrayList<MessageModel> readMessagesByConversationId(String conversationId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "
                        + CONTENT + ", "
                        + CREATION_TIME + ", "
                        + SENDER_ID + ", "
                        + RECIPIENT_ID + ", "
                        + ID
                        + " FROM " + TABLE_MESSAGES
                        + " WHERE " + CONVERSATION_ID + " =?"
                        + " ORDER BY " + CREATION_TIME,
                new String[]{conversationId});

        ArrayList<MessageModel> messageList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                messageList.add(new TindarMessage(
                        cursor.getString(0),
                        new Timestamp(cursor.getLong(1)),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        conversationId));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return messageList;
    }
}

