package com.anjac.sakthi.anjacalumni;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.anjac.sakthi.anjacalumni.libs.ConnectionDetector;
import com.anjac.sakthi.anjacalumni.libs.RRManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class Register2 extends AppCompatActivity {
String[] array=new String[]{};
String fname,lname,pno,pass,mail,yearss,dobs,rollnos;
CourseAdapter courseAdapter;
Spinner course,branches;
    String courses,dep,places;
EditText year,dob,rollno,place;
private int myear,mMonth,mDay;
ArrayList<CourseItem> courseItemArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Register register=new Register();
        year=findViewById(R.id.year);
        place=findViewById(R.id.place);
        dob=findViewById(R.id.dob);
        rollno=findViewById(R.id.rollno);
       Bundle bundle=getIntent().getExtras();
       initlist();
       branches=findViewById(R.id.branches);
       course=findViewById(R.id.courses);
        courseAdapter=new CourseAdapter(this,courseItemArrayAdapter);
        course.setAdapter(courseAdapter);

        dob.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        final Calendar c=Calendar.getInstance();
        myear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog=new DatePickerDialog(Register2.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int years, int month, int day) {
                dob.setText(years+"-"+(month+1)+"-"+day);
            }
        },myear,mMonth,mDay);
        datePickerDialog.show();
    }
});

        course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CourseItem selectedcourse=(CourseItem)adapterView.getItemAtPosition(i);
                if(selectedcourse.getCoursename()=="BSc")
                {

bsc();
courses="BSc";
                }
                if(selectedcourse.getCoursename()=="BCom")
                {
            courses="BCom";
                    bcom();
                }
                if(selectedcourse.getCoursename().equals("BA"))
                {
                    courses="BA";
ba();
                }
                if(selectedcourse.getCoursename().equals("BBA"))
                {
courses="BBA";
                }
                if(selectedcourse.getCoursename().equals("PHS"))
                {
courses="PHS";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(Register2.this,"Select Your Course",Toast.LENGTH_SHORT).show();
            }
        });
        fname=bundle.getString("fname");
        lname=bundle.getString("lname");
        mail=bundle.getString("mail");
        pno=bundle.getString("pno");
        pass=bundle.getString("pass");
//       System.out.println(fname);
//       System.out.println(lname);
//       System.out.println(mail);
//        System.out.println(pno);
//        System.out.println(pass);
;
       }
     private void initlist()
     {
         courseItemArrayAdapter=new ArrayList<>();
         courseItemArrayAdapter.add(new CourseItem("Select Your Course"));
         courseItemArrayAdapter.add(new CourseItem("BSc"));
         courseItemArrayAdapter.add(new CourseItem("BCom"));
         courseItemArrayAdapter.add(new CourseItem("BA"));
         courseItemArrayAdapter.add(new CourseItem("BBA"));
         courseItemArrayAdapter.add(new CourseItem("PHS"));

     }

     private void bsccourses()
     {
         courseItemArrayAdapter=new ArrayList<>();
         courseItemArrayAdapter.add(new CourseItem("Branch Of Your Course"));
         courseItemArrayAdapter.add(new CourseItem("CS"));
         courseItemArrayAdapter.add(new CourseItem("Zoology"));
         courseItemArrayAdapter.add(new CourseItem("Botany"));
         courseItemArrayAdapter.add(new CourseItem("BBA"));
         courseItemArrayAdapter.add(new CourseItem("PHS"));

     }
     private void bsc()
     {
         bsccourses();
         courseAdapter=new CourseAdapter(this,courseItemArrayAdapter);
         branches.setAdapter(courseAdapter);
         branches.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 CourseItem selectedcourse=(CourseItem)adapterView.getItemAtPosition(i);
                 if(selectedcourse.getCoursename()=="CS")
                 {

                     dep="CS";
                 }
                 if(selectedcourse.getCoursename()=="Zoology")
                 {
                     dep="Zoology";
                 }
                 if(selectedcourse.getCoursename()=="Botany")
                 {
                     dep="Botany";
                 }

             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {
                 Toast.makeText(Register2.this,"Select Your Branch of Your Course",Toast.LENGTH_SHORT).show();
             }
         });
     }
    private void ba()
    {
        bacourses();
        courseAdapter=new CourseAdapter(this,courseItemArrayAdapter);
        branches.setAdapter(courseAdapter);
        branches.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CourseItem selectedcourse=(CourseItem)adapterView.getItemAtPosition(i);
                if(selectedcourse.getCoursename()=="Tamil")
                {

                    dep="Tamil";
                }
                if(selectedcourse.getCoursename()=="English")
                {
                    dep="English";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void bacourses()
    {

        courseItemArrayAdapter=new ArrayList<>();
        courseItemArrayAdapter.add(new CourseItem("Branch Of Your course"));
        courseItemArrayAdapter.add(new CourseItem("Tamil"));
        courseItemArrayAdapter.add(new CourseItem("English"));
    }
    private void bcom()
    {
        bcomcourses();
        courseAdapter=new CourseAdapter(this,courseItemArrayAdapter);
        branches.setAdapter(courseAdapter);
   branches.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
       @Override
       public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
           CourseItem selectedcourse=(CourseItem)adapterView.getItemAtPosition(i);
           if(selectedcourse.getCoursename()=="CS")
           {

               dep="CS";
           }
           if(selectedcourse.getCoursename()=="CA")
           {
               dep="CA";
           }

       }

       @Override
       public void onNothingSelected(AdapterView<?> adapterView) {

       }
   });
    }
    private void bcomcourses()
    {
        courseItemArrayAdapter=new ArrayList<>();
        courseItemArrayAdapter.add(new CourseItem("Branch Of Your course"));
        courseItemArrayAdapter.add(new CourseItem("CA"));
        courseItemArrayAdapter.add(new CourseItem("CS"));
    }

    public void register(View view) {
        yearss=year.getText().toString();
        dobs=dob.getText().toString();
        rollnos=rollno.getText().toString();
        places=place.getText().toString();
        String ref_url="http://192.168.43.193/Android/register.php";
        if (new ConnectionDetector(this).isConnectingToInternet()) {
           try {
//                Log.d(">>>>>>",user.getText().toString());
                new RRManager(this).setRequestMethod("POST").setLoadingMsg("Please Wait")
                        .setURL(ref_url).setParams(new JSONObject().put("tag","register").put("fname",fname)
                        .put("lname",lname).put("mail",mail).put("pno",pno).put("pass",pass).put("course",courses).put("branch",dep).
                        put("year",yearss).put("dob",dobs).put("rollno",rollnos).put("place",places))
                        .setResponseListner(new RRManager.ResponseListner() {
                            @Override
                            public void onResponse(JSONObject params, String response) throws JSONException {
                                Log.d(">>>>>>>>>Json", response);

                                Log.d(">>>>>>>",fname);
                                Log.d(">>>>>>>",lname);
                                Log.d(">>>>>>>",mail);
                                Log.d(">>>>>>>",pno);
                                Log.d(">>>>>>",pass);
                                Log.d(">>>>>>",dobs);
                                Log.d(">>>>>",yearss);
                                Log.d(">>>>>>",rollnos);
                                JSONObject object = new JSONObject(response);
                                if (object.has("error_msg") && object.getInt("error_msg") == 0 ) {
                                    Toast.makeText(Register2.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                    Intent i=new Intent(Register2.this,UserLogin.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(Register2.this, "Try Again Later", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).execute();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}

