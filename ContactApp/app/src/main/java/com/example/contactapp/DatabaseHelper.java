package com.example.contactapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    //SQL Properties
    public static final int DATABASE_VERSION = 1;
    public static final String DATABSE_NAME = "Contact2019.db";
    public static final String TABLE_NAME = "Contact2019_table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "contact";
    public static final String COLUMN_NAME_PHONE_NUMBER = "number";
    public static final String COLUMN_NAME_AGE = "age";

    //SQL Commands
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME +
            " (" + ID + " INTEGER PRIMARY KEY_AUTOINCREMENT," + COLUMN_NAME_CONTACT + " TEXT, " + COLUMN_NAME_PHONE_NUMBER + " INTEGER)";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABSE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase(); //TODO: Remove this testing database
        Log.d("MyContactApp", "DatabaseHelper: constructed database helper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
