package com.cmpe283.vmhealthmonitor.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cmpe283.vmhealthmonitor.models.Users;

/**
 * Created by Varun on 5/1/2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "vmhealthmonitor";

    // Directories table name
    private static final String TABLE_USERS = "users";

    // Directory Table Columns names
    //private static final String KEY_USERS_ID = "id";
    private static final String KEY_USERS_NAME = "username";
    private static final String KEY_USERS_PASSWORD = "pwd";
    private static final String KEY_USERS_ROLE = "role";
    /*private static final String KEY_USERS_HOST_URL = "hostURL";
    private static final String KEY_USERS_HOST_NAME = "hostusername";
    private static final String KEY_USERS_HOST_PASSWORD = "hostpwd";
    */

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DIRECTORIES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "("
                + KEY_USERS_NAME + " TEXT PRIMARY KEY, "
                + KEY_USERS_PASSWORD + " TEXT"
                + KEY_USERS_ROLE + " TEXT" +")";
        db.execSQL(CREATE_DIRECTORIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    public void createUser(Users users){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERS_NAME, users.getUserName());
        values.put(KEY_USERS_PASSWORD, users.getPassword());
        values.put(KEY_USERS_ROLE, users.getRole());
        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }

    public Users getUser(String userName){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] {KEY_USERS_NAME,
                        KEY_USERS_PASSWORD, KEY_USERS_ROLE}, KEY_USERS_NAME + "=?",
                new String[] { String.valueOf(userName) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Users user = new Users(cursor.getColumnName(0), cursor.getColumnName(1), cursor.getColumnName(2));
        // return Directory
        return user;
    }

}
