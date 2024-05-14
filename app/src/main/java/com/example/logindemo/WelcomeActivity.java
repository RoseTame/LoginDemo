package com.example.logindemo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Toast.makeText(
                WelcomeActivity.this,
                "登录成功",
                Toast.LENGTH_LONG
        ).show();
    }
}