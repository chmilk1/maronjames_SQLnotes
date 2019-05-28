package com.example.contactapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper dbh = new DatabaseHelper(getApplicationContext());

        myDB = new DatabaseHelper(this);
        Log.d("MyContactApp", "MainActivity: myDB created");
    }

    public boolean insertData(String name, int phoneNumber, int age){
        Log.d("MyContactApp", "DatabaseHelper: inserting data");
        SQLiteDatabase db = myDB.getWritableDatabase();
        ContentValues cvs = new ContentValues();
        cvs.put(myDB.COLUMN_NAME_CONTACT, name);
        cvs.put(myDB.COLUMN_NAME_PHONE_NUMBER, phoneNumber);
        cvs.put(myDB.COLUMN_NAME_AGE, age);
        long result = db.insert(myDB.TABLE_NAME, null, cvs);
        if(result == -1){
            Log.d("MyContactApp", "DatabaseHelper: inserting data - FAILED");
            return false;
        } else {
            Log.d("MyContactApp", "DatabaseHelper: inserting data - PASSED");
            return true;
        }
    }
}
