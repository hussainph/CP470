package com.phal1880.androidassignments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    private static final String ACTIVITY_NAME = "ChatDatabaseHelper";

    public static final String DATABASE_NAME = "Messages.db";
    public static final Integer VERSION_NUM = 2;

    public static final String TABLE_NAME = "messages";
    public static final String KEY_ID = "msg_id";
    public static final String KEY_MESSAGE = "message";

    private static final String DATABASE_CREATE = "create table " + TABLE_NAME
            + " (" + KEY_ID  + " integer primary key autoincrement, "
            + KEY_MESSAGE + " text not null);";

    private static final String DATABASE_UPGRADE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(ACTIVITY_NAME, "Calling onCreate");
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(ACTIVITY_NAME, "Calling onUpgrade, oldVersion="
                + oldVersion + " newVersion=" + newVersion);
        db.execSQL(DATABASE_UPGRADE);
        onCreate(db);
    }
}
