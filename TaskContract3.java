package com.example.pravar.sidepanel;

import android.provider.BaseColumns;

public class TaskContract3 {
    public static final String DB_NAME = "com.aziflaj.todolist3.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks3";

        public static final String COL_TASK_TITLE = "title3";
    }
}
