package com.anjac.sakthi.anjacalumni;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    EditText cpass,fname,lname,mail,pass,pno;
    Button next;
    private int dum=0;
    private static final Pattern PASSWORD_PATTERN=Pattern.compile("^"+
            "(?=.*[0-9])"+ //atleast one number
            "(?=.*[a-z])"+ //atleast one lower case
            "(?=.*[A-Z])"+  //atleast one Upper case
            "(?=.*[@#$%^&+=])"+ //atleast one special character
            "(?=.\\S+$)"+ //no white spaces
            ".{8,}"+ //atleast 8 characters
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
         if(fname.getText().toString().isEmpty())
         {
             fname.setError("Fill First Name");
         dum+=1;
         }
         if(lname.getText().toString().isEmpty())
         {
             lname.setError("Fill Last Name");
             dum+=1;
         }
         if(mail.getText().toString().isEmpty())
         {
            mail.setError("Fill Mail id");
             dum+=1;
         }
         else if (!(mail.getText().toString().contains("@gmail.com")||mail.getText().toString().contains("@yahoo.com")))
         {
             mail.setError("Fill Valid Mail id");
             mail.setText("");
             dum+=1;
         }
         if(pno.getText().toString().isEmpty())
         {
             pno.setError("Fill Mobile No");
             dum+=1;
         }
         else if (pno.getText().toString().length()>10 || pno.getText().toString().length()<10)
         {
             pno.setError("Fill valid Mobile No ");
             pno.setText("");
             dum+=1;
         }
         if(pass.getText().toString().isEmpty())
         {
             pass.setError("Fill Password ");
             dum+=1;
         }
         else if (!PASSWORD_PATTERN.matcher(pass.getText().toString()).matches())
         {
             pass.setError("Password must contain 1 lower case,upper case,number,special character and length greater than 8");
             pass.setText("");
             dum+=1;
         }
         if(cpass.getText().toString().isEmpty())
         {
          cpass.setError("Fill Confirm Password ");
             dum+=1;
         }
         if(!pass.getText().toString().equals(cpass.getText().toString()))
         {
             pass.setError("Mismatch Password");
             cpass.setText("");
             dum+=1;
         }
             if(dum==0) {
                 Intent i = new Intent(Register.this, Register2.class);
                 Bundle bundle = new Bundle();
                 bundle.putString("fname", fname.getText().toString());
                 bundle.putString("lname",lname.getText().toString());
                 bundle.putString("pno",pno.getText().toString());
                 bundle.putString("mail",mail.getText().toString());
                 bundle.putString("pass",pass.getText().toString());
                 i.putExtras(bundle);
                 startActivity(i);
             }
         }
 });
    }
}
