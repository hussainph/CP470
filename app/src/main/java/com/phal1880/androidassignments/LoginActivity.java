package com.phal1880.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "LoginActivity";
    public static final String prefString = "LoginDetails";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        final Button loginButton = findViewById(R.id.loginButton);
        SharedPreferences prefs = getSharedPreferences(prefString, Context.MODE_PRIVATE);
        final TextView loginText = findViewById(R.id.editTextTextEmailAddress2);
        loginText.setText(prefs.getString("DefaultEmail", "email@domain.com"));
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loginStore();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    void loginStore() {
        final TextView loginText = findViewById(R.id.editTextTextEmailAddress2);
        SharedPreferences prefs = getSharedPreferences(prefString, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("DefaultEmail", loginText.getText().toString());
        edit.commit();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}