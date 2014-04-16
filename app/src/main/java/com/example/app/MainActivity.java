package com.example.app;

import android.app.Activity;
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

import java.util.ArrayList;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onShowClick(MenuItem v) {
        Log.d(myLogger, "show clicked");
        if(v.getItemId() == R.id.btnShow) {
            Cursor cursor = getContentResolver().query(STUDENTS_URI, null, null,
                    null, null);
            String[] from = new String[] {MyContentProvider.STUDENTS_NAME, MyContentProvider.STUDENTS_SURNAME, MyContentProvider.STUDENTS_AGE};
            int[] to = new int[] {R.id.tvName, R.id.tvSurname, R.id.tvAge};
            scAdapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to );
            lvItems = (ListView) findViewById(R.id.lvItems);
            lvItems.setAdapter(scAdapter);

        }
    }
    public void onDeleteClick(MenuItem v) {
        Log.d(myLogger, "delete clicked");
        if (v.getItemId() == R.id.btnDelete) {
            int cnt = getContentResolver().delete(STUDENTS_URI, null , null);
            Log.d(myLogger, "deleted " + cnt + " values");
        }
    }
    public void onAddClick(MenuItem v) {
        Log.d(myLogger, "add clicked");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnDelete:
                onDeleteClick(item);
                return true;
            case R.id.btnShow:
                onShowClick(item);
                return true;
            case R.id.btnAdd:
                onAddClick(item);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }


}




