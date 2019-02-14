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

public class Activation extends AppCompatActivity {
String mailid,otpno;
    EditText mail,otp;
Button otpbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);
   mail=findViewById(R.id.mail);
   otp=findViewById(R.id.otp);
   otpbutton=findViewById(R.id.activate);

    }

    public void checkotp(View view) {
        mailid=mail.getText().toString();
        otpno=otp.getText().toString();
        String ref_url = "http://192.168.43.193/Android/active.php";
        if (new ConnectionDetector(this).isConnectingToInternet()) {
            try {

                new RRManager(this).setRequestMethod("POST").setLoadingMsg("Please Wait")
                        .setURL(ref_url).setParams(new JSONObject().put("tag", "register").put("mail",mailid)
                        .put("otp",otpno))
                        .setResponseListner(new RRManager.ResponseListner() {
                            @Override
                            public void onResponse(JSONObject params, String response) throws JSONException {
                                JSONObject object = new JSONObject(response);

                                    if (object.has("error_msg") && object.getInt("error_msg") == 0 ) {
                                        Toast.makeText(Activation.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Activation.this, UserLogin.class);
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(Activation.this,"Check your OTP",Toast.LENGTH_SHORT).show();
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
