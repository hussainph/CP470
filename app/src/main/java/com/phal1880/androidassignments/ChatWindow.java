package com.phal1880.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.phal1880.androidassignments.ChatDatabaseHelper.KEY_MESSAGE;
import static com.phal1880.androidassignments.ChatDatabaseHelper.TABLE_NAME;

public class ChatWindow extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "ChatWindow";
    ArrayList<String> chatMsg = new ArrayList<>();

    ChatDatabaseHelper chatHelper;
    SQLiteDatabase writer;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        // Assignment 2 Part 3
        final Button sendButton = findViewById(R.id.sendButton);
        final EditText chatBox = findViewById(R.id.editTextTextShortMessage);
        final ListView listView = findViewById(R.id.listView);


        // Assignment 3 Part 1
        chatHelper = new ChatDatabaseHelper(ChatWindow.this);
        writer = chatHelper.getWritableDatabase();
        ContentValues cValues  = new ContentValues();

        getColumnCount();

        // Didn't use ? because everytime I try, it breaks the code and forces infinite loop
        cursor = writer.rawQuery("SELECT message from messages", new String[] {});

        //in this case, “this” is the ChatWindow, which is-A Context object
        ChatAdapter messageAdapter =new ChatAdapter( this );
        listView.setAdapter (messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatMsg.add(chatBox.getText().toString());

                cValues.put(KEY_MESSAGE, chatBox.getText().toString());
                writer.insert(TABLE_NAME, "NullPlaceholder", cValues);
                messageAdapter.notifyDataSetChanged(); // This restarts the process of getCount()/getView()
                chatBox.setText("");
            }
        });

        cursor.moveToFirst();
        for (int i=0; i < cursor.getCount(); i++) {
            String message = cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));
            chatMsg.add(message);
            cursor.moveToNext();
        }

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString( cursor.getColumnIndex( ChatDatabaseHelper.KEY_MESSAGE) ) );
            cursor.moveToNext();
        }

        Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + cursor.getColumnCount() );


    }

    @Override
    protected void onDestroy() {
        cursor.close();
        writer.close();
        super.onDestroy();
    }

    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return chatMsg.size();
        }

        public String getItem(int position) {
            return chatMsg.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            }
            else {
                result = inflater.inflate(R.layout.chat_row_ongoing, null);
            }

            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }
    }

    private void getColumnCount() {
        cursor = writer.rawQuery("SELECT * from messages", new String[] {});

        for(int i = 0; i < cursor.getColumnCount(); i++){
            String columns = cursor.getColumnName(i);
            System.out.println("Columns: " + columns);
            cursor.moveToNext();
        }

        Log.i(ACTIVITY_NAME, "Cursor’s column count =" + cursor.getColumnCount() );
    }

}