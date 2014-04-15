package com.example.app;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public final static String myLogger = "myLogger";
    private ListView lvItems;
    private MyAdapter myAdapter;
    private ArrayList<StudentDataSet> sds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Thread.sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void onSendClick(View v) {
        if(v.getId() == R.id.btnSend) {
            final Uri STUDENTS_URI = Uri
                    .parse("content://com.example.app.provider/students");
            Cursor cursor = getContentResolver().query(STUDENTS_URI, null, null,
                    null, null);
            int nameIndex = cursor.getColumnIndex("name");
            int surnameIndex = cursor.getColumnIndex("surname");
            int ageIndex = cursor.getColumnIndex("age");
            sds = new ArrayList<StudentDataSet>();
            if (cursor.moveToFirst()) {
                String name = cursor.getString(nameIndex);
                String surname = cursor.getString(surnameIndex);
                int age = cursor.getInt(ageIndex);
                sds.add(new StudentDataSet(name, surname, age));
                Log.d(myLogger, "name = " + name + " surname = " + surname + " age = " + age);
            }
            myAdapter = new MyAdapter(this, sds);
            lvItems = (ListView) findViewById(R.id.lvItems);
            lvItems.setAdapter(myAdapter);

        }
    }



}




