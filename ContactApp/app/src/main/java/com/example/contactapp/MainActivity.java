package com.example.contactapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText editName;
    EditText editPhone;
    EditText editAge;
    Button addContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper dbh = new DatabaseHelper(getApplicationContext());

        editName = findViewById(R.id.nameText);
        editPhone = findViewById(R.id.phoneText);
        editAge = findViewById(R.id.ageText);

        addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(v);
            }
        });

        myDB = new DatabaseHelper(this);
        Log.d("MyContactApp", "MainActivity: myDB created");
    }

    public void addData(View view){
        Log.d("MyContactApp", "MainActivity: addData called");
        boolean isInserted = myDB.insertData(editName.getText().toString(), Integer.parseInt(editPhone.getText().toString()), Integer.parseInt(editAge.getText().toString()) );
        if(isInserted){
            Toast.makeText(this, "Contact Inserted!", Toast.LENGTH_LONG);
        } else {
            Toast.makeText(this, "Contact Insert FAILED.", Toast.LENGTH_LONG);
        }
    }
}
