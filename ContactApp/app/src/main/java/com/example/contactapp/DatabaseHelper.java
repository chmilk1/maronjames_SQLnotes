package com.example.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
            " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME_CONTACT + " TEXT, " + COLUMN_NAME_PHONE_NUMBER + " TEXT, "
            + COLUMN_NAME_AGE + " TEXT)";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABSE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase(); //TODO: Remove this testing database
        Log.d("MyContactApp", "DatabaseHelper: constructed database helper");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyContactApp", "DatabaseHelper: onCreate Ran");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyContactApp", "DatabaseHelper: onUpdate Ran");
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public boolean insertData(String name, String phoneNumber, String age){
        Log.d("MyContactApp", "DatabaseHelper: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvs = new ContentValues();
        cvs.put(COLUMN_NAME_CONTACT, name);
        cvs.put(COLUMN_NAME_PHONE_NUMBER, phoneNumber);
        cvs.put(COLUMN_NAME_AGE, age);
        long result = db.insert(TABLE_NAME, null, cvs);
        if(result == -1){
            Log.d("MyContactApp", "DatabaseHelper: inserting data - FAILED");
            return false;
        } else {
            Log.d("MyContactApp", "DatabaseHelper: inserting data - PASSED");
            return true;
        }
    }

    public Cursor getAllData(){
        Log.d("MyContactApp", "DatabaseHelper: getting all data");
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);
    }


}
