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
import com.courseproject.tindar.usecases.editfilters.EditFiltersDsResponseModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsGateway;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsResponseModel;
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
import java.util.Date;
import java.util.GregorianCalendar;

public class DatabaseHelper extends SQLiteOpenHelper implements EditProfileDsGateway, EditFiltersDsGateway, LoginDsGateway, SignUpDsGateway, LikeListDsGateway, ViewProfilesDsGateway, UserListDsGateway, EditAccountDsGateway {
    private static DatabaseHelper dbInstance;
    private static DatabaseHelper testDbInstance;

    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String TABLE_LIKES = "likes";
    private static final String TABLE_MATCHES = "matches";

    private static final String ID = "id";
    private static final String IS_ACTIVE_STATUS = "is_active_status";
    private static final String EMAIL = "is_email";
    private static final String PASSWORD = "password";
    private static final String DISPLAY_NAME = "display_name";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String BIRTHDATE = "birthdate";
    private static final String GENDER = "gender";
    private static final String LOCATION = "location";
    private static final String PROFILE_PICTURE_LINK = "profile_picture_link";
    private static final String ABOUT_ME = "about_me";
    private static final String PREFERRED_GENDERS = "preferred_genders";
    private static final String PREFERRED_LOCATIONS = "preferred_locations";
    private static final String PREFERRED_AGE_MINIMUM = "preferred_age_minimum";
    private static final String PREFERRED_AGE_MAXIMUM = "preferred_age_maximum";
    private static final String USER_ID = "user_id";
    private static final String LIKED_USER_ID = "liked_user_id";
    private static final String USER_ID_1 = "user_id_1";
    private static final String USER_ID_2 = "user_id_2";

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

    private static final String CREATE_TABLE_LIKES_QUERY = "CREATE TABLE " + TABLE_LIKES + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INTEGER, "
            + LIKED_USER_ID + " INTEGER, "
            + "FOREIGN KEY (" + USER_ID + ") REFERENCES " + TABLE_ACCOUNTS + "(" + ID + "), "
            + "FOREIGN KEY (" + LIKED_USER_ID + ") REFERENCES " + TABLE_ACCOUNTS + "(" + ID + "), "
            + "UNIQUE(" + USER_ID + ", " + LIKED_USER_ID + "));";

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

    // access modifier is private so DatabaseHelper doesn't get directly instantiated. The instantiation of
    // DatabaseHelper should go through getInstance method.
    private DatabaseHelper(@Nullable Context context, String databaseName) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ACCOUNTS_QUERY);
        db.execSQL(CREATE_TABLE_LIKES_QUERY);
        db.execSQL(CREATE_TABLE_MATCHES_QUERY);
        addInitialData(db);
    }

    // TODO: find an alternative way to alter tables when the database is upgraded so the tables are not
    //  deleted everytime the database is upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIKES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATCHES);
        onCreate(db);
    }

    /**
     * Adds dummy data to the database. This is for a demo purpose of the app.
     *
     * @param db database instance
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

    public String addAccount(boolean isActiveStatus, String email, String password, String displayName, String firstName,
                             String lastName, java.util.Date birthdate, String gender, String location,
                             String profilePictureLink, String aboutMe, String preferredGenders,
                             String preferredLocations, int preferredAgeMinimum, int preferredAgeMaximum) {

        SQLiteDatabase db = this.getReadableDatabase();

        return addAccount(isActiveStatus, email, password, displayName, firstName, lastName, birthdate, gender, location,
                profilePictureLink, aboutMe, preferredGenders, preferredLocations, preferredAgeMinimum,
                preferredAgeMaximum, db);
    }

    @Override
    public String addAccount(SignUpDsRequestModel signUpDsRequestModel) {
        return addAccount(true, signUpDsRequestModel.getEmail(), signUpDsRequestModel.getPassword(),
                signUpDsRequestModel.getDisplayName(), "", "", null, "", "",
                "", "", "", "", 19, 999);
    }

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

    @Override
    public EditProfileDsResponseModel readProfile(String userId) {
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

        EditProfileDsResponseModel dsResponse = new EditProfileDsResponseModel(
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

    @Override
    public void updateBirthdate(String userId, Date birthdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BIRTHDATE, new java.sql.Date(birthdate.getTime()).getTime());

        db.update(TABLE_ACCOUNTS, cv, ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public void updateGender(String userId, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GENDER, gender);

        db.update(TABLE_ACCOUNTS, cv, ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public void updateLocation(String userId, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LOCATION, location);

        db.update(TABLE_ACCOUNTS, cv, ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public void updateProfilePictureLink(String userId, String profilePictureLink) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PROFILE_PICTURE_LINK, profilePictureLink);

        db.update(TABLE_ACCOUNTS, cv, ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public void updateAboutMe(String userId, String aboutMe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ABOUT_ME, aboutMe);

        db.update(TABLE_ACCOUNTS, cv, ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public EditFiltersDsResponseModel readFilters(String userId) {
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

        EditFiltersDsResponseModel dsResponse = new EditFiltersDsResponseModel(
                preferredGenders,
                preferredLocations,
                cursor.getInt(2),
                cursor.getInt(3)
        );

        cursor.close();
        return dsResponse;
    }

    @Override
    public void updatePreferredGenders(String userId, ArrayList<String> preferredGenders) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PREFERRED_GENDERS, String.join(", ", preferredGenders));

        db.update(TABLE_ACCOUNTS, cv, ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public void updatePreferredLocations(String userId, ArrayList<String> preferredLocations) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PREFERRED_LOCATIONS, String.join(", ", preferredLocations));

        db.update(TABLE_ACCOUNTS, cv, ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public void updatePreferredAgeGroup(String userId, int minAge, int maxAge) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PREFERRED_AGE_MINIMUM, minAge);
        cv.put(PREFERRED_AGE_MAXIMUM, maxAge);

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

    public void deleteAllDbRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_ACCOUNTS);
        db.execSQL("delete from " + TABLE_LIKES);
        db.execSQL("delete from " + TABLE_MATCHES);
    }

    /** Check if either userId or other have liked each other
     * @param userId user who initiated a 'like' interaction
     * @param otherUserId user receiving a 'like' from userId
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

    /** Returns an ArrayList<LikeListDsResponseModel> that is used to allow display names to be
     * shown on screen when plugged into MatchListFragment. Essentially returning a list of
     * display names
     * @param userIds users who's display names we are retrieving
     */
    @Override
    public ArrayList<LikeListDsResponseModel> readDisplayNames(ArrayList<String> userIds) {
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

