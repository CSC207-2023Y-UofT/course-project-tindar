package com.courseproject.tindar.ds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.courseproject.tindar.usecases.editaccount.EditAccountDsGateway;
import com.courseproject.tindar.usecases.editaccount.EditAccountDsResponseModel;
import com.courseproject.tindar.usecases.editfilters.EditFiltersDsGateway;
import com.courseproject.tindar.usecases.editfilters.EditFiltersModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsGateway;
import com.courseproject.tindar.usecases.editprofile.EditProfileRequestModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileResponseModel;
import com.courseproject.tindar.usecases.login.LoginDsGateway;
import com.courseproject.tindar.usecases.likelist.LikeListDsGateway;
import com.courseproject.tindar.usecases.likelist.LikeListDsResponseModel;
import com.courseproject.tindar.usecases.userlist.UserListDsGateway;
import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesDsGateway;
import com.courseproject.tindar.usecases.viewprofiles.ViewProfilesDsResponseModel;
import com.courseproject.tindar.usecases.signup.SignUpDsGateway;
import com.courseproject.tindar.usecases.signup.SignUpDsRequestModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * Database Helper which implements database methods for Edit Profile, Edit Filters, Login, Sign Up, Like List, View
 * Profile, User List, Edit Account features. This also implements method on creating database, on upgrading
 * database, and to get database instance.
 */
public class DatabaseHelper extends SQLiteOpenHelper implements EditProfileDsGateway, EditFiltersDsGateway, LoginDsGateway, SignUpDsGateway, LikeListDsGateway, ViewProfilesDsGateway, UserListDsGateway, EditAccountDsGateway {
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
    private DatabaseHelper(@Nullable Context context, String databaseName) {
        // access modifier is private so DatabaseHelper doesn't get directly instantiated. The instantiation of
        // DatabaseHelper should go through getInstance method.
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
    }

    /**
     * Adds dummy data to the database. This is for a demo purpose of the app.
     *
     * @param db the database instance
     **/
    private void addInitialData(SQLiteDatabase db) {
        addAccount(true, "jack@someemail.com", "password_jack", "jack",
                "Jack", "Brown", new GregorianCalendar(2000, 1, 26).getTime(),
                "Male", "Toronto", "https://media.cnn.com/api/v1/images/stellar/prod/190503220200-spongebob-squarepants-story-top.jpg?q=x_2,y_0,h_1041,w_1849,c_crop/h_720,w_1280", "Hi", "Female, Other",
                "Toronto", 20, 25, db);
        addAccount(true, "amy@someotheremail.com", "password_amy", "amy",
                "Amy", "Smith", new GregorianCalendar(2000, 7, 2).getTime(),
                "Female", "Montreal", "https://assets.nick.com/uri/mgid:arc:imageassetref:shared.nick.us:5232d654-03b3-458e-b30e-37a09e7492bd?quality=0.7&gen=ntrn&legacyStatusCode=true", "Hello","Male",
                "Montreal, Toronto", 23, 27, db);
        addAccount(true, "bell@exampleemail.com", "somepassword", "bell",
                "Bell", "Robin", new GregorianCalendar(2003, 9, 5).getTime(),
                "Female", "Calgary", "https://assets.ayobandung.com/crop/0x0:0x0/750x500/webp/photo/2023/02/27/Snapinstaapp_1080_332750966_200-1013192027.jpg", "I would like to",
                "Female, Male", "Calgary, Vancouver", 19, 999, db);
        addAccount(true, "rogers@exampleemail.com", "someotherpassword", "roger",
                "roger", "fido", new GregorianCalendar(2003, 12, 3).getTime(),
                "Female", "Calgary", "https://ccc", "I would like to",
                "Female, Male", "Calgary, Vancouver", 19, 999, db);
        addAccount(true, "telus@exampleemail.com", "somethirdpassword", "ted",
                "ted", "telus", new GregorianCalendar(2001, 12, 3).getTime(),
                "Male", "Toronto", "https://ccc", "I would like to",
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
    public EditProfileResponseModel readProfile(String userId) {
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

        EditProfileResponseModel dsResponse = new EditProfileResponseModel(
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

    /**
     * Takes a userId and returns a representation of the corresponding profile
     * @param userId of the profile to be retrieved
     * @return ViewProfilesDsResponseModel representing the profile with this userId
     */
    @Override
    public ViewProfilesDsResponseModel readNextProfile(String userId) {
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

        ViewProfilesDsResponseModel dsResponse = new ViewProfilesDsResponseModel(
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

    /**
     * Checks if either of two users has liked the other
     * @param userId id of user who we are trying to check if he/she likes other user
     * @param otherUserId userId of user receiving a "like"
     * @return true if either user has liked the other; false otherwise
     */
    public boolean checkLiked(String userId, String otherUserId) {
        // Check check if either userId or other have liked each other
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

    /**
     * Adds a matched pair to the database.
     * Precondition: userId < otherUserId.
     * @param userId lesser userId in the match
     * @param otherUserId greater userId in the match
     * @param db database that this match will be added to
     */
    public void addToMatched(String userId, String otherUserId, SQLiteDatabase db) {
        // Add userId and otherUserId to match list after a match has occurred
        ContentValues cv = new ContentValues();
        cv.put(USER_ID_1, userId);
        cv.put(USER_ID_2, otherUserId);

        db.insert(TABLE_MATCHES, null, cv);
    }

    /**
     * Adds a matched pair to the database.
     * Precondition: userId < otherUserId.
     * @param userId lesser userId in the match
     * @param otherUserId greater userId in the match
     */
    @Override
    public void addToMatched(String userId, String otherUserId) {
        // Calls addToMatched with userId values from LikeListInteractor
        SQLiteDatabase db = this.getWritableDatabase();
        addToMatched(userId, otherUserId, db);
    }

    /**
     * Adds that userId likes otherUserId in the database.
     * @param userId of the 'liker'
     * @param otherUserId userId of the 'likee'
     * @param db database that this like will be added to
     */
    public void addLike(String userId, String otherUserId, SQLiteDatabase db) {
        // Adds otherUserId and userId like each other to database
        ContentValues cv = new ContentValues();
        cv.put(USER_ID, userId);
        cv.put(LIKED_USER_ID, otherUserId);

        db.insert(TABLE_LIKES, null, cv);
    }

    /**
     * Adds that userId likes otherUserId in the database.
     * @param userId of the 'liker'
     * @param otherUserId userId of the 'likee'
     */
    @Override
    public void addLike(String userId, String otherUserId) {
        // Calls addLike above with userId values from LikeListInteractor
        SQLiteDatabase db = this.getWritableDatabase();
        addLike(userId, otherUserId, db);
    }

    /**
     * Removes the record that userId likes otherUserId in the database.
     * @param userId of the 'liker'
     * @param otherUserId userId of the 'likee'
     */
    @Override
    public void removeLike(String userId, String otherUserId) {
        // Removes userId and otherUserId from like list in database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_ID, userId);
        cv.put(LIKED_USER_ID, otherUserId);

        db.delete(TABLE_LIKES, USER_ID + "= ? and " + LIKED_USER_ID + " = ?",
                new String[]{userId, otherUserId});
    }

    /**
     * Removes a matched pair fom the database.
     * Precondition: userId < otherUserId.
     * @param userId lesser userId in the match
     * @param otherUserId greater userId in the match
     */
    @Override
    public void removeFromMatched(String userId, String otherUserId) {
        // Removes userId and otherUserId from database match list
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_ID_1, userId);
        cv.put(USER_ID_2, otherUserId);

        db.delete(TABLE_MATCHES, USER_ID_1 + "= ? and " + USER_ID_2 + " = ?",
                new String[]{userId, otherUserId});
    }

    /**
     * Returns a user's match list in the form of userIds using their userId
     * @param userId of the user whose match list is to be retrieved
     * @return ArrayList<String[]> of userIds of those who have matched with this user
     */
    @Override
    public ArrayList<String[]> readMatchList(String userId) {
        // Reads match list from database and returns ArrayList<String[]> of userIds
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

    /**
     * Returns a list of display names corresponding to a list of userIds
     * @param userIds of the users
     * @return ArrayList<String[]> of the display names of the users with these userIds.
     *          The display name at index i is the display name of the user with userId userIds[i].
     */
    @Override
    public ArrayList<LikeListDsResponseModel> readDisplayNames(ArrayList<String> userIds) {
        // Returns an ArrayList<LikeListDsResponseModel> that is used to allow display names to
        // be shown on screen when plugged into MatchListFragment, essentially returns
        // a list of display names
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

        ArrayList<LikeListDsResponseModel> displayNamesResponse = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                displayNamesResponse.add(new LikeListDsResponseModel(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return displayNamesResponse;
    }

    @Override
    public ArrayList<String> getAllUserIds() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "
                + ID
                + " FROM " + TABLE_ACCOUNTS, null);

        ArrayList<String> userIdsResponse = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                userIdsResponse.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return userIdsResponse;
    }
}

