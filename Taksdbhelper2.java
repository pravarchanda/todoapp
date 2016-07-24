package com.example.pravar.sidepanel;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Taksdbhelper2 extends SQLiteOpenHelper {
    public Taksdbhelper2(Context context) {
        super(context, TaskContract2.DB_NAME, null, TaskContract2.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TaskContract2.TaskEntry.TABLE + " ( " +
                TaskContract2.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskContract2.TaskEntry.COL_TASK_TITLE + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskContract2.TaskEntry.TABLE);
        onCreate(db);
    }
}
