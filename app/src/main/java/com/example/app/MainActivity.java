package com.example.app;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentValues;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.Random;

public class MainActivity extends Activity implements LoaderCallbacks<Cursor> {
    public final static String myLogger = "myLogger";
    private ListView lvItems;
    private int LOADER_ID = 1;

    final Uri STUDENTS_URI = Uri
            .parse("content://com.example.app.provider/students");

    private SimpleCursorAdapter scAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLoaderManager().initLoader(LOADER_ID, null, this);
//            Cursor cursor = getContentResolver().query(STUDENTS_URI, null, null,
//                    null, null);
            String[] from = new String[] {MyContentProvider.STUDENTS_NAME, MyContentProvider.STUDENTS_SURNAME, MyContentProvider.STUDENTS_AGE};
            int[] to = new int[] {R.id.tvName, R.id.tvSurname, R.id.tvAge};
            scAdapter = new SimpleCursorAdapter(this, R.layout.item, null, from, to, 0 );
            lvItems = (ListView) findViewById(R.id.lvItems);
            lvItems.setAdapter(scAdapter);

    }

    public void onShowClick(View v) {
        Log.d(myLogger, "show clicked");
        if(v.getId() == R.id.btnShow) {
//            Cursor cursor = getContentResolver().query(STUDENTS_URI, null, null,
//                    null, null);
//            String[] from = new String[] {MyContentProvider.STUDENTS_NAME, MyContentProvider.STUDENTS_SURNAME, MyContentProvider.STUDENTS_AGE};
//            int[] to = new int[] {R.id.tvName, R.id.tvSurname, R.id.tvAge};
//            scAdapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to, 0 );
//            lvItems = (ListView) findViewById(R.id.lvItems);
//            lvItems.setAdapter(scAdapter);
            getLoaderManager().getLoader(LOADER_ID).forceLoad();

        }
    }
    public void onDeleteClick(View v) {
        Log.d(myLogger, "delete clicked");
        if (v.getId() == R.id.btnDelete) {
            int cnt = getContentResolver().delete(STUDENTS_URI, null , null);
            Log.d(myLogger, "deleted " + cnt + " values");
            getLoaderManager().getLoader(LOADER_ID).forceLoad();
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
            Log.d(myLogger, "adding new name " + cv.get(MyContentProvider.STUDENTS_NAME) + ", surname = " + cv.get(MyContentProvider.STUDENTS_SURNAME));
            getLoaderManager().getLoader(LOADER_ID).forceLoad();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new MyCursorLoader(this, STUDENTS_URI);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        scAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
    }
}





