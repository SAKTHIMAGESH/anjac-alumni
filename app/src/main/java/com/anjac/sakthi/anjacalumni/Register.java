package com.anjac.sakthi.anjacalumni;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anjac.sakthi.anjacalumni.libs.ConnectionDetector;
import com.anjac.sakthi.anjacalumni.libs.RRManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    EditText cpass,fname,lname,mail,pass,pno;
    Button next;
    String mails;
    Boolean dum;
    private static final Pattern PASSWORD_PATTERN=Pattern.compile("^"+
            "(?=.*[0-9])"+ //atleast one number
            "(?=.*[a-z])"+ //atleast one lower case
            "(?=.*[A-Z])"+  //atleast one Upper case
            "(?=.*[@#$%^&+=])"+ //atleast one special character
            "(?=.\\S+$)"+ //no white spaces
            ".{6,}"+ //atleast 8 characters
            "$"); //end of string
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    fname=findViewById(R.id.fname);
    lname=findViewById(R.id.lname);
    mail=findViewById(R.id.mail);
    pass=findViewById(R.id.rpassword);
    cpass=findViewById(R.id.crpassword);
    pno=findViewById(R.id.phone);
    next=findViewById(R.id.next);
 next.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
mails=mail.getText().toString();
dum=true;
String ref_url = "http://192.168.43.193/Android/checkMail.php";
         if (new ConnectionDetector(Register.this).isConnectingToInternet()) {
             try {
                 new RRManager(Register.this).setRequestMethod("POST").setLoadingMsg("Please Wait")
                         .setURL(ref_url).setParams(new JSONObject().put("tag", "register").put("mail", mails))
                         .setResponseListner(new RRManager.ResponseListner() {
                             @Override
                             public void onResponse(JSONObject params, String response) throws JSONException {
                                 Log.d(">>>>>>>>>Json", response);
                                 JSONObject object = new JSONObject(response);

                                     if (object.getString("msg").equals("exists")&& object.getInt("error_msg")==0 &&object.getInt("con")==0 ) {
                                         mail.setError("Mail id already exists");
                                         dum=false;
                                         Log.d(">>>>>>>>>>>>", String.valueOf(dum));
//                                             Intent i=new Intent(UserLogin.this,MainActivity.class);
//                                             startActivity(i);}
                                 }
                             }
                         }).execute();
             } catch (JSONException e) {
                 e.printStackTrace();
             }
         }
Log.d(">>>>>>>>>>>>>>>",String.valueOf(dum));
         if (fname.getText().toString().isEmpty()) {
             fname.setError("Fill First Name");
             dum = false;
         }
         if (lname.getText().toString().isEmpty()) {
             lname.setError("Fill Last Name");
             dum = false;
         }
         if (mail.getText().toString().isEmpty()) {
             mail.setError("Fill Mail id");
             dum =false ;
         } else if (!(mail.getText().toString().contains("@gmail.com") || mail.getText().toString().contains("@yahoo.com"))) {
             mail.setError("Fill Valid Mail id");
             mail.setText("");
             dum = false;
         }
         if (pno.getText().toString().isEmpty()) {
             pno.setError("Fill Mobile No");
             dum = false;
         } else if (pno.getText().toString().length() > 10 || pno.getText().toString().length() < 10) {
             pno.setError("Fill valid Mobile No ");
             pno.setText("");
             dum = false;
         }
         if (pass.getText().toString().isEmpty()) {
             pass.setError("Fill Password ");
             dum = false;
         } else if (!PASSWORD_PATTERN.matcher(pass.getText().toString()).matches()) {
             pass.setError("Password must contain 1 lower case,upper case,number,special character and length greater than 8");
             pass.setText("");
             dum =false ;
         }
         if (cpass.getText().toString().isEmpty()) {
             cpass.setError("Fill Confirm Password ");
             dum =false ;
         }
         if (!pass.getText().toString().equals(cpass.getText().toString())) {
             cpass.setError("Mismatch Password");
             cpass.setText("");
             dum =false ;
         }
         if (dum != false) {
             Intent i = new Intent(Register.this, Register2.class);
             Bundle bundle = new Bundle();
             bundle.putString("fname", fname.getText().toString());
             bundle.putString("lname", lname.getText().toString());
             bundle.putString("pno", pno.getText().toString());
             bundle.putString("mail", mail.getText().toString());
             bundle.putString("pass", pass.getText().toString());
             i.putExtras(bundle);
             startActivity(i);
         }
         if (dum ==false)
         {
             Toast.makeText(Register.this,"Fill the Necessary Fields",Toast.LENGTH_SHORT).show();
 dum=true;
         }
         }
 });

    }


}
