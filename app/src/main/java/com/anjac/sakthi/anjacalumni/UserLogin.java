package com.anjac.sakthi.anjacalumni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anjac.sakthi.anjacalumni.libs.ConnectionDetector;
import com.anjac.sakthi.anjacalumni.libs.RRManager;

import org.json.JSONException;
import org.json.JSONObject;

public class UserLogin extends AppCompatActivity implements View.OnClickListener {

    EditText user,pass;
    Button login;
    String mail,passs;
    TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
    user=findViewById(R.id.username);
    pass=findViewById(R.id.password);
    login=findViewById(R.id.login);
    signup=findViewById(R.id.signup);


    signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(UserLogin.this,Register.class);
            startActivity(i);
        }
    });
    }
    public void loginuser(View view)
    {
        mail=user.getText().toString();
        passs=pass.getText().toString();
        if(mail.equals("") && passs.equals("")){
            Toast.makeText(UserLogin.this,"Fill Mail Id and Password",Toast.LENGTH_SHORT).show();
    }
    if(mail!=null && passs==null)
        {
            pass.setError("Fill Password");
        }
     if(passs!=null && mail==null)
        {
            user.setError("Fill Mail id");
        }

            String ref_url = "http://192.168.43.193/Android/login.php";
            if (new ConnectionDetector(this).isConnectingToInternet()) {
                try {
                    Log.d(">>>>>>", user.getText().toString());
                    new RRManager(this).setRequestMethod("POST").setLoadingMsg("Please Wait")
                            .setURL(ref_url).setParams(new JSONObject().put("tag", "register").put("pass", passs)
                            .put("mail",mail))
                            .setResponseListner(new RRManager.ResponseListner() {
                                @Override
                                public void onResponse(JSONObject params, String response) throws JSONException {
                                    Log.d(">>>>>>>>>Json", response);
                                    JSONObject object = new JSONObject(response);
                                    if (object.has("con") && object.getInt("con") != 1) {
                                        if (object.has("error_msg") && object.getInt("error_msg") == 0 && object.getInt("active") == 1) {
                                            Toast.makeText(UserLogin.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(UserLogin.this, MainActivity.class);
                                            startActivity(i);
                                        }
                                        if (object.has("error_msg") && object.getInt("error_msg") == 11 && object.getInt("active") == 0) {
                                            Toast.makeText(UserLogin.this, "Activate Your Account", Toast.LENGTH_SHORT).show();
                                        }
                                        if (object.has("error_msg") && object.getInt("error_msg") == 11 && object.getInt("active") == 1) {
                                            Toast.makeText(UserLogin.this, "Check Mail id and Password", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(UserLogin.this, "Try Again Later Server Busy", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View view) {

    }
}
