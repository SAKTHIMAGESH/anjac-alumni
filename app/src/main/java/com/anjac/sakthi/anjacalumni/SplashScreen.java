package com.anjac.sakthi.anjacalumni;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.rel);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        relativeLayout.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashScreen.this,UserLogin.class);
                startActivity(i);
            }
        },3000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
