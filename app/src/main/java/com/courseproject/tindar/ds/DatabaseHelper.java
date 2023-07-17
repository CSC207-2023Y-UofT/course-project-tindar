package com.courseproject.tindar.ds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.courseproject.tindar.usecases.editprofile.EditProfileDsGateway;
import com.courseproject.tindar.usecases.editprofile.EditProfileResponseModel;

import java.util.Date;
import java.util.GregorianCalendar;

public class DatabaseHelper extends SQLiteOpenHelper implements EditProfileDsGateway {

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

    private static final String CREATE_TABLE_ACCOUNTS_QUERY = "CREATE TABLE " + TABLE_ACCOUNTS + " ("
        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + COLUMN_IS_ACTIVE_STATUS + " NUMBER(1), "
        + COLUMN_EMAIL + " VARCHAR(30), "
        + COLUMN_PASSWORD + " VARCHAR(30), "
        + COLUMN_DISPLAY_NAME + " VARCHAR(30), "
        + COLUMN_FIRST_NAME + " VARCHAR(30), "
        + COLUMN_LAST_NAME + " VARCHAR(30), "
        + COLUMN_BIRTHDATE + " DATE, "
        + COLUMN_GENDER + " VARCHAR(30), "
        + COLUMN_LOCATION + " VARCHAR(30), "
        + COLUMN_PROFILE_PICTURE_LINK + " TEXT, "
        + COLUMN_ABOUT_ME + " TEXT);";

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
            "Male", "Toronto", "aaa", "Hi", db);
        addAccount(true, "amy@someotheremail.com", "password_amy", "amy",
            "Amy", "Smith", new GregorianCalendar(2000, 7, 2).getTime(),
            "Female", "Montreal", "bbb", "Hello", db);
    }

    private String addAccount(boolean isActiveStatus, String email, String password, String displayName,
                              String firstName,
                              String lastName, java.util.Date birthdate, String gender, String location,
                              String profilePictureLink, String aboutMe, SQLiteDatabase db) {

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

        long insertedId = db.insert(TABLE_ACCOUNTS, null, cv);
        return String.valueOf(insertedId);
    }

    public String addAccount(boolean isActiveStatus, String email, String password, String displayName, String firstName,
                             String lastName, java.util.Date birthdate, String gender, String location,
                             String profilePictureLink, String aboutMe) {

        SQLiteDatabase db = this.getReadableDatabase();

        return addAccount(isActiveStatus, email, password, displayName, firstName, lastName, birthdate, gender,
            location, profilePictureLink, aboutMe, db);
    }

    @Override
    public EditProfileResponseModel readProfile(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorProfile = db.rawQuery("SELECT "
                + COLUMN_BIRTHDATE + ", "
                + COLUMN_GENDER + ", "
                + COLUMN_LOCATION + ", "
                + COLUMN_PROFILE_PICTURE_LINK + ", "
                + COLUMN_ABOUT_ME
                + " FROM " + TABLE_ACCOUNTS
                + " WHERE " + COLUMN_ID + " =?",
            new String[]{userId});

        cursorProfile.moveToFirst();

        EditProfileResponseModel profileResponse = new EditProfileResponseModel(
            new java.util.Date(cursorProfile.getLong(0)),
            cursorProfile.getString(1),
            cursorProfile.getString(2),
            cursorProfile.getString(3),
            cursorProfile.getString(4)
        );

        cursorProfile.close();
        return profileResponse;
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
}
