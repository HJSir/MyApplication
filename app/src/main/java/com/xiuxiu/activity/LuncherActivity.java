package com.xiuxiu.activity;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.MainActivity;
import com.R;

public class LuncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luncher);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startMainActivity();
            }
        },2000);
    }

    private void startMainActivity() {

        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
