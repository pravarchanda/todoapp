package com.example.pravar.sidepanel;

import android.provider.BaseColumns;

public class TaskContract2 {
    public static final String DB_NAME = "com.aziflaj.todolist2.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks2";

        public static final String COL_TASK_TITLE = "title2";
    }
}
