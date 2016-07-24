package com.example.pravar.sidepanel;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Taskdbhelper3 extends SQLiteOpenHelper {
    public Taskdbhelper3(Context context) {
        super(context, TaskContract3.DB_NAME, null, TaskContract3.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TaskContract3.TaskEntry.TABLE + " ( " +
                TaskContract3.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskContract3.TaskEntry.COL_TASK_TITLE + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskContract3.TaskEntry.TABLE);
        onCreate(db);
    }
}
