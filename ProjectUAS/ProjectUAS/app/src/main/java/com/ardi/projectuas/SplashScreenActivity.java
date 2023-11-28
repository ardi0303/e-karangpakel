package com.ardi.projectuas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Home = new Intent(SplashScreenActivity.this,
                        LoginActivity.class);
                startActivity(Home);
                //startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                finish();
            }
        },3000);
    }
}