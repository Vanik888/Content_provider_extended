package com.example.app;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.apache.http.entity.ContentProducer;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {
    public final static String myLogger = "myLogger";
    private ListView lvItems;
    final Uri STUDENTS_URI = Uri
            .parse("content://com.example.app.provider/students");

    private SimpleCursorAdapter scAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onShowClick(View v) {
        Log.d(myLogger, "show clicked");
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
        Log.d(myLogger, "delete clicked");
        if (v.getId() == R.id.btnDelete) {
            int cnt = getContentResolver().delete(STUDENTS_URI, null , null);
            Log.d(myLogger, "deleted " + cnt + " values");
        }
    }
    public void onAddClick(View v) {
        if (v.getId() == R.id.btnAdd) {

            ContentValues cv = new ContentValues();
            Random r = new Random();
            int max = 25;
            int randomValue = r.nextInt(max)+1;
            cv.put(MyContentProvider.STUDENTS_NAME, "user_" + randomValue );
            cv.put(MyContentProvider.STUDENTS_SURNAME, "surname_"  + randomValue );
            cv.put(MyContentProvider.STUDENTS_AGE, 1 + randomValue );
            Uri newUri = getContentResolver().insert(STUDENTS_URI, cv);
            Log.d(myLogger, "add clicked, new uri = " + newUri);
        }
    }
}




