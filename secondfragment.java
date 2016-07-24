package com.example.pravar.sidepanel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class secondfragment extends Fragment {
    View view;
    private Taksdbhelper2 mHelper;
    private ListView mTaskListView;
    private ArrayAdapter mAdapter;
    FloatingActionButton btn2;
    Button btndel2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.secondlayout,container,false);
        ((MainActivity) getActivity()).setactionbartitle("Secondfragment");
        btn2 = (FloatingActionButton) view.findViewById(R.id.buttonabc2);
        mTaskListView = (ListView) view.findViewById(R.id.list_todo2);
        mHelper = new Taksdbhelper2(getContext());
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(TaskContract2.TaskEntry.TABLE,
                new String[]{TaskContract2.TaskEntry._ID, TaskContract2.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);
        while(cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(TaskContract2.TaskEntry.COL_TASK_TITLE);
        }
        cursor.close();
        db.close();
        mTaskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {

                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("Task Completed")
                        .setMessage("delete this task")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String str = String.valueOf(position);
                                String iss = (String) mTaskListView.getItemAtPosition(position);
                                View paren = (View) view.getParent();
                                TextView taskTextView = (TextView) paren.findViewById(R.id.task_title);
                                TextView tasktextcol = (TextView) paren.findViewById(R.id.textViewcol);
                                Toast.makeText(getContext(),iss,Toast.LENGTH_SHORT).show();
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                db.delete(TaskContract2.TaskEntry.TABLE,
                                        TaskContract2.TaskEntry.COL_TASK_TITLE + " = ?",
                                        new String[]{iss});
                                db.close();
                                updateUI();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return false;
            }

        });

        mTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = String.valueOf(position);
                String iss = (String) mTaskListView.getItemAtPosition(position);
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("Your task details")
                        .setMessage(iss)
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText taskEditText = new EditText(getContext());
                AlertDialog dialog = new AlertDialog.Builder(getContext())
                        .setTitle("Add a new task")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(TaskContract2.TaskEntry.COL_TASK_TITLE, task);
                                db.insertWithOnConflict(TaskContract2.TaskEntry.TABLE,
                                        null,
                                        values,
                                        SQLiteDatabase.CONFLICT_REPLACE);
                                updateUI();
                                db.close();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();

            }
        });

        updateUI();
        return view;

    }
    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(TaskContract2.TaskEntry.TABLE,
                new String[]{TaskContract2.TaskEntry._ID, TaskContract2.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(TaskContract2.TaskEntry.COL_TASK_TITLE);
            taskList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(getContext(),
                    R.layout.itemtodo2,
                    R.id.task_title,
                    taskList);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }

}

