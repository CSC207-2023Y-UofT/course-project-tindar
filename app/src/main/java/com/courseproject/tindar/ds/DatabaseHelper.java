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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatabaseHelper extends SQLiteOpenHelper implements EditProfileDsGateway, EditFiltersDsGateway {
    private static DatabaseHelper dbInstance;

    private static final String TABLE_ACCOUNTS = "accounts";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IS_ACTIVE_STATUS = "is_active_status";
    private static final String COLUMN_EMAIL = "is_email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_DISPLAY_NAME = "display_name";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_BIRTHDATE = "birthdate";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_PROFILE_PICTURE_LINK = "profile_picture_link";
    private static final String COLUMN_ABOUT_ME = "about_me";
    private static final String COLUMN_PREFERRED_GENDERS = "preferred_genders";
    private static final String COLUMN_PREFERRED_LOCATIONS = "preferred_locations";
    private static final String COLUMN_PREFERRED_AGE_MINIMUM = "preferred_age_minimum";
    private static final String COLUMN_PREFERRED_AGE_MAXIMUM = "preferred_age_maximum";

    private static final String CREATE_TABLE_ACCOUNTS_QUERY = "CREATE TABLE " + TABLE_ACCOUNTS + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_IS_ACTIVE_STATUS + " NUMBER(1) NOT NULL, "
            + COLUMN_EMAIL + " VARCHAR(30) NOT NULL, "
            + COLUMN_PASSWORD + " VARCHAR(30) NOT NULL, "
            + COLUMN_DISPLAY_NAME + " VARCHAR(30), "
            + COLUMN_FIRST_NAME + " VARCHAR(30), "
            + COLUMN_LAST_NAME + " VARCHAR(30), "
            + COLUMN_BIRTHDATE + " DATE, "
            + COLUMN_GENDER + " VARCHAR(30), "
            + COLUMN_LOCATION + " VARCHAR(30), "
            + COLUMN_PROFILE_PICTURE_LINK + " TEXT, "
            + COLUMN_ABOUT_ME + " TEXT, "
            + COLUMN_PREFERRED_GENDERS + " TEXT NOT NULL, "
            + COLUMN_PREFERRED_LOCATIONS + " TEXT NOT NULL, "
            + COLUMN_PREFERRED_AGE_MINIMUM + " TEXT NOT NULL, "
            + COLUMN_PREFERRED_AGE_MAXIMUM + " TEXT NOT NULL);";

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
        addInitialData(db);
    }

    // TODO: find an alternative way to alter tables when the database is upgraded so the tables are not
    //  deleted everytime the database is upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
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
        cv.put(COLUMN_IS_ACTIVE_STATUS, isActiveStatus ? 1 : 0);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_DISPLAY_NAME, displayName);
        cv.put(COLUMN_FIRST_NAME, firstName);
        cv.put(COLUMN_LAST_NAME, lastName);
        cv.put(COLUMN_BIRTHDATE, new java.sql.Date(birthdate.getTime()).getTime());
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_PROFILE_PICTURE_LINK, profilePictureLink);
        cv.put(COLUMN_ABOUT_ME, aboutMe);
        cv.put(COLUMN_PREFERRED_GENDERS, preferredGenders);
        cv.put(COLUMN_PREFERRED_LOCATIONS, preferredLocations);
        cv.put(COLUMN_PREFERRED_AGE_MINIMUM, preferredAgeMinimum);
        cv.put(COLUMN_PREFERRED_AGE_MAXIMUM, preferredAgeMaximum);

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
                        + COLUMN_BIRTHDATE + ", "
                        + COLUMN_GENDER + ", "
                        + COLUMN_LOCATION + ", "
                        + COLUMN_PROFILE_PICTURE_LINK + ", "
                        + COLUMN_ABOUT_ME
                        + " FROM " + TABLE_ACCOUNTS
                        + " WHERE " + COLUMN_ID + " =?",
                new String[]{userId});

        cursor.moveToFirst();

        EditProfileDsResponseModel dsResponse = new EditProfileDsResponseModel(
                new java.util.Date(cursor.getLong(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)
        );

        cursor.close();
        return dsResponse;
    }

    @Override
    public void updateBirthdate(String userId, Date birthdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BIRTHDATE, new java.sql.Date(birthdate.getTime()).getTime());

        db.update(TABLE_ACCOUNTS, cv, COLUMN_ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public void updateGender(String userId, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_GENDER, gender);

        db.update(TABLE_ACCOUNTS, cv, COLUMN_ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public void updateLocation(String userId, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LOCATION, location);

        db.update(TABLE_ACCOUNTS, cv, COLUMN_ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public void updateProfilePictureLink(String userId, String profilePictureLink) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PROFILE_PICTURE_LINK, profilePictureLink);

        db.update(TABLE_ACCOUNTS, cv, COLUMN_ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public void updateAboutMe(String userId, String aboutMe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ABOUT_ME, aboutMe);

        db.update(TABLE_ACCOUNTS, cv, COLUMN_ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public EditFiltersDsResponseModel readFilters(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "
                        + COLUMN_PREFERRED_GENDERS + ", "
                        + COLUMN_PREFERRED_LOCATIONS + ", "
                        + COLUMN_PREFERRED_AGE_MINIMUM + ", "
                        + COLUMN_PREFERRED_AGE_MAXIMUM
                        + " FROM " + TABLE_ACCOUNTS
                        + " WHERE " + COLUMN_ID + " =?",
                new String[]{userId});

        cursor.moveToFirst();

        // converts comma separated string to an ArrayList. If string is an empty string, it creates an empty ArrayList.
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
        cv.put(COLUMN_PREFERRED_GENDERS, String.join(", ", preferredGenders));

        db.update(TABLE_ACCOUNTS, cv, COLUMN_ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public void updatePreferredLocations(String userId, ArrayList<String> preferredLocations) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PREFERRED_LOCATIONS, String.join(", ", preferredLocations));

        db.update(TABLE_ACCOUNTS, cv, COLUMN_ID + "=?", new String[]{userId});
        db.close();
    }

    @Override
    public void updatePreferredAgeGroup(String userId, int minAge, int maxAge) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PREFERRED_AGE_MINIMUM, minAge);
        cv.put(COLUMN_PREFERRED_AGE_MAXIMUM, maxAge);

        db.update(TABLE_ACCOUNTS, cv, COLUMN_ID + "=?", new String[]{userId});
        db.close();
    }
}

