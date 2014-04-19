package com.example.app;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by vanik on 19.04.14.
 */
public class MyCursorLoader extends CursorLoader {
    private Uri uri;
    private Context context;

    public MyCursorLoader(Context context, Uri uri) {
        super(context);
        this.uri = uri;
        this.context = context;
    }

    @Override
    public Cursor loadInBackground() {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        return cursor;
    }
}
