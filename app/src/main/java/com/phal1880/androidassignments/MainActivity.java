package com.phal1880.androidassignments;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "MainActivity";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        final Button listViewCaller = findViewById(R.id.listActivityButton);
        final Button startChat = findViewById(R.id.start_chat);

        final Button startToolbarTest = findViewById(R.id.start_tool_test);
        final Button openWeather = findViewById(R.id.weather);

        listViewCaller.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 10);
            }
        });

        startChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatWindow.class);
                startActivity(intent);
            }
        });

        startToolbarTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestToolbar.class);
                startActivity(intent);
            }
        });

        openWeather.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeatherForecast.class);
                startActivity(intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");
            String messagePassed = data.getStringExtra("Response");
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, messagePassed, duration);
            toast.show();
        }

    }

    @Override

    public void onBackPressed() {

        super.onBackPressed();

        setResult(Activity.RESULT_CANCELED);

        finish();

    }
}