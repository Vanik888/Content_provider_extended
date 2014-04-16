package com.example.app;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public final static String myLogger = "myLogger";
    private ListView lvItems;
    final Uri STUDENTS_URI = Uri
            .parse("content://com.example.app.provider/students");

    private SimpleCursorAdapter scAdapter;

    private ArrayList<StudentDataSet> sds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onShowClick(View v) {
        if(v.getId() == R.id.btnShow) {
            Cursor cursor = getContentResolver().query(STUDENTS_URI, null, null,
                    null, null);
            String[] from = new String[] {MyContentProvider.STUDENTS_NAME, MyContentProvider.STUDENTS_SURNAME, MyContentProvider.STUDENTS_AGE};
            int[] to = new int[] {R.id.tvName, R.id.tvSurname, R.id.tvAge};
            scAdapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to );
            lvItems = (ListView) findViewById(R.id.lvItems);
            lvItems.setAdapter(scAdapter);

        }
    }
    public void onDeleteClick(View v) {
        if (v.getId() == R.id.btnDelete) {
            int cnt = getContentResolver().delete(STUDENTS_URI, null , null);
            Log.d(myLogger, "deleted " + cnt + " values");
        }
    }



}




