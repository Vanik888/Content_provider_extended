package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vanik on 16.04.14.
 */
public class MyAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater layoutInflater;
    private ArrayList<StudentDataSet> students;

    public MyAdapter(Context ctx, ArrayList<StudentDataSet> students) {
        this.ctx = ctx;
        this.students = students;
        this.layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view  = layoutInflater.inflate(R.layout.item, parent, false);
        }
        StudentDataSet sds = (StudentDataSet) getItem(i);
        ((TextView) view.findViewById(R.id.tvName)).setText(sds.getName());
        ((TextView) view.findViewById(R.id.tvSurname)).setText(sds.getSurname());
        ((TextView) view.findViewById(R.id.tvAge)).setText(String.valueOf(sds.getAge()));
        return view;
    }

}
