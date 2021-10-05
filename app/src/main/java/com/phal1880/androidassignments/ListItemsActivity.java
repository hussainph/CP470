package com.phal1880.androidassignments;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        final ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraTrigger(imageButton);
            }
        });

        final Switch switchButton = findViewById(R.id.switch1);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnCheckChanged(switchButton);
            }
        });

        final CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckChanged(checkBox);
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


    protected void setOnCheckChanged(Switch switchButton) {
        if (switchButton.isChecked()) {
            CharSequence text = "Switch is On";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(ListItemsActivity.this, text, duration);
            toast.show();
        }
        else {
            CharSequence text = "Switch is Off";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(ListItemsActivity.this, text, duration);
            toast.show();
        }
    }

    protected void onCheckChanged(CheckBox checkBox) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to finish this Activity?"); // Text

        // Add the buttons
        builder.setPositiveButton(R.string.ok, new /*positive Button*/
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        // User clicked OK button
                        Intent resultIntent = new Intent(  );
                        resultIntent.putExtra("Response", "ListItemsActivity passed: My information to share");
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                });

        builder.setNegativeButton(R.string.cancel, new /*negative Button */
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel(); // User cancelled the dialog
                    }
                });
        // Create the AlertDialog
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    protected void cameraTrigger(ImageButton imageButton) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageButton btnImg = findViewById(R.id.imageButton);
            btnImg.setImageBitmap(imageBitmap);

            Log.i(ACTIVITY_NAME, "Returned to ListItemsActivity.onActivityResult");
        }
    }
}