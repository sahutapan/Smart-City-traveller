package com.travel.smartcitytraveller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    //variable
    ImageView logo;
    TextView appName;
  private static int DELAY=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo=findViewById(R.id.logo);
        appName=findViewById(R.id.name);

        //logo.animate().translationY(-1600).setDuration(1000).setDuration(3000);
       // appName.animate().translationY(1400).setDuration(1000).setDuration(3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent  intent=new Intent(SplashScreen.this,Login.class);
                startActivity(intent);
                finish();
            }
        },DELAY);

    }
}