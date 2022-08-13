package com.example.foru;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }

        }, 3000L);
    }
}