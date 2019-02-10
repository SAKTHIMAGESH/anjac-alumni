package com.anjac.sakthi.anjacalumni;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseAdapter extends ArrayAdapter<CourseItem> {
    public CourseAdapter(Context context, ArrayList<CourseItem> courseList)
    {
         super(context,0,courseList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initview(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initview(position,convertView,parent);
    }
    private View initview(int position,View convertView,ViewGroup parent)
    {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.course_spinner_row,parent,false);
            }
        TextView course=convertView.findViewById(R.id.course_name);
        CourseItem courseItem=getItem(position);
        if(courseItem!=null) {
            course.setText(courseItem.getCoursename());
        }
        return convertView;
    }
}
