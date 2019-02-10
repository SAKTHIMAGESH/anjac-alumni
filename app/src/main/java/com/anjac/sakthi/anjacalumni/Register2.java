package com.anjac.sakthi.anjacalumni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class Register2 extends AppCompatActivity {
String[] array=new String[]{};
CourseAdapter courseAdapter;
ArrayList<CourseItem> courseItemArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Register register=new Register();
       Bundle bundle=getIntent().getExtras();
       initlist();
        Spinner coursespinner=findViewById(R.id.courses);
        courseAdapter=new CourseAdapter(this,courseItemArrayAdapter);
        coursespinner.setAdapter(courseAdapter);

        coursespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CourseItem selectedcourse=(CourseItem)adapterView.getItemAtPosition(i);
                if(selectedcourse.getCoursename()=="BSc")
                {

                }
                if(selectedcourse.getCoursename()=="BCom")
                {

                }
                if(selectedcourse.getCoursename().equals("BA"))
                {

                }
                if(selectedcourse.getCoursename().equals("BBA"))
                {

                }
                if(selectedcourse.getCoursename().equals("PHS"))
                {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       String fname=bundle.getString("name");
       String lname=bundle.getString("lname");
       String mail=bundle.getString("mail");
       String pno=bundle.getString("pno");
       String pass=bundle.getString("pass");
       System.out.println(fname);
       System.out.println(lname);
       System.out.println(mail);
        System.out.println(pno);
        System.out.println(pass);

//       Log.d(">>>>>>>",fname);
//       Log.d(">>>>>>>",lname);
//       Log.d(">>>>>>>",mail);
//       Log.d(">>>>>>>",pno);
//       Log.d(">>>>>>",pass);
    }
     private void initlist()
     {
         courseItemArrayAdapter=new ArrayList<>();
         courseItemArrayAdapter.add(new CourseItem("BSc"));
         courseItemArrayAdapter.add(new CourseItem("BCom"));
         courseItemArrayAdapter.add(new CourseItem("BA"));
         courseItemArrayAdapter.add(new CourseItem("BBA"));
         courseItemArrayAdapter.add(new CourseItem("PHS"));

     }

}
