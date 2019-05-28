package com.example.contactapp;

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
}
