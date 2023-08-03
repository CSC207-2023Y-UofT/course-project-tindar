package com.courseproject.tindar.ds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.courseproject.tindar.usecases.editfilters.EditFiltersDsGateway;
import com.courseproject.tindar.usecases.editfilters.EditFiltersDsResponseModel;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsGateway;
import com.courseproject.tindar.usecases.editprofile.EditProfileDsResponseModel;
import com.courseproject.tindar.usecases.likelist.LikeListDsGateway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatabaseHelper extends SQLiteOpenHelper implements EditProfileDsGateway, EditFiltersDsGateway,
        LikeListDsGateway {
    private static DatabaseHelper dbInstance;

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
            + EMAIL + " VARCHAR(30) NOT NULL, "
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
     * Returns DatabaseHelper instance. If the instance already exists it returns exiting instance, if not, it creates
     * a instance and returns it. This insures there is only one instance of DatabaseHelper for the application.
     *
     * @param context an activity's context
     * @return an instance of DatabaseHelper
     **/
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (dbInstance == null) {
            // Use the application context
            dbInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return dbInstance;
    }

    // access modifier is private so DatabaseHelper doesn't get directly instantiated. The instantiation of
    // DatabaseHelper should go through getInstance method.
    private DatabaseHelper(@Nullable Context context) {
        super(context, "tindar.db", null, 1);
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
                "Male", "Toronto", "aaa", "Hi", "Female, Other",
                "Toronto", 20, 25, db);
        addAccount(true, "amy@someotheremail.com", "password_amy", "amy",
                "Amy", "Smith", new GregorianCalendar(2000, 7, 2).getTime(),
                "Female", "Montreal", "bbb", "Hello","Male",
                "Montreal, Toronto", 23, 27, db);
    }

    private String addAccount(boolean isActiveStatus, String email, String password, String displayName,
                              String firstName,
                              String lastName, java.util.Date birthdate, String gender, String location,
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
        cv.put(BIRTHDATE, new java.sql.Date(birthdate.getTime()).getTime());
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

    // precondition: userId < otherUserId. This is to avoid duplicates of
    // (userId, otherUserId) and (otherUserId, userId)
    public void addToMatched(String userId, String otherUserId, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(USER_ID_1, userId);
        cv.put(USER_ID_2, otherUserId);

        db.insert(TABLE_MATCHES, null, cv);
    }

    @Override
    public void addToMatched(String userId, String otherUserId) {
        SQLiteDatabase db = this.getWritableDatabase();
        addToMatched(userId, otherUserId, db);
    }

    public void addLike(String userId, String otherUserId, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(USER_ID, userId);
        cv.put(LIKED_USER_ID, otherUserId);

        db.insert(TABLE_LIKES, null, cv);
    }
    @Override
    public void addLike(String userId, String otherUserId) {
        SQLiteDatabase db = this.getWritableDatabase();
        addLike(userId, otherUserId, db);
    }

    @Override
    public void removeLike(String userId, String otherUserId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_ID, userId);
        cv.put(LIKED_USER_ID, otherUserId);

        db.delete(TABLE_LIKES, USER_ID + "= ? and " + LIKED_USER_ID + " = ?",
                new String[]{userId, otherUserId});
    }

    // precondition: userId < otherUserId. Since matches should not have duplicate of
    // (userId, otherUserId) and (otherUserId, userId), we should respect the order when
    // adding/deleting records of userId pair.
    @Override
    public void removeFromMatched(String userId, String otherUserId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_ID_1, userId);
        cv.put(USER_ID_2, otherUserId);

        db.delete(TABLE_MATCHES, USER_ID_1 + "= ? and " + USER_ID_2 + " = ?",
                new String[]{userId, otherUserId});
    }

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
}

