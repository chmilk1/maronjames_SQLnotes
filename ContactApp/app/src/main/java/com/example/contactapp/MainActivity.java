package com.example.contactapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
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
    Button showContantButton;
    Button searchButton;

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

        showContantButton = findViewById(R.id.showData);
        showContantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewData(v);
            }
        });

        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchData(v);
            }
        });

        myDB = new DatabaseHelper(this);
        Log.d("MyContactApp", "MainActivity: myDB created");
    }

    public void addData(View view){
        Log.d("MyContactApp", "MainActivity: addData called");
        boolean isInserted = myDB.insertData(editName.getText().toString(), editPhone.getText().toString(), editAge.getText().toString() );
        if(isInserted){
            Toast.makeText(this, "Contact Inserted!", Toast.LENGTH_LONG);
        } else {
            Toast.makeText(this, "Contact Insert FAILED.", Toast.LENGTH_LONG);
        }
    }

    public void viewData(View view){
        Cursor res = myDB.getAllData();

        if(res.getCount() <= 0){
            showMessage("No data");
            return;
        }

        StringBuilder sb = new StringBuilder();
        while(res.moveToNext()){
            sb.append("ID: " + res.getString(0) + "\nName: " + res.getString(1) + "\nPhoneNumber: " + res.getString(2) + "\nAge: " + res.getString(3) +"\n");
        }
        showMessage(sb.toString());
    }

    public void searchData(View view){
        Cursor res = myDB.getAllData();

        if(res.getCount() <= 0){
            showMessage("No data");
            return;
        }

        StringBuilder sb = new StringBuilder();
        while(res.moveToNext()){
            boolean matches = false;
            if(editName.getText().toString().length() > 1){
                if(editName.getText().toString().equals(res.getString(1))){
                    matches = true;
                } else {
                    matches = false;
                }
            }
            if(editPhone.getText().toString().length() > 1){
                if(editPhone.getText().equals(res.getString(2))){
                    matches = true;
                } else {
                    matches = false;
                }
            }
            if(editAge.getText().toString().length() > 1){
                if(editPhone.getText().equals(res.getString(2))){
                    matches = true;
                } else {
                    matches = false;
                }
            }

            if(matches){
                sb.append("ID: " + res.getString(0) + "\nName: " + res.getString(1) + "\nPhoneNumber: " + res.getString(2) + "\nAge: " + res.getString(3) +"\n");
            }
        }
        showMessage(sb.toString());
    }

    private void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.show();
    }
}
