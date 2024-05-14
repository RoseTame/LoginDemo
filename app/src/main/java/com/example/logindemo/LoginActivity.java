package com.example.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    Button btn_exit;
    TextView tv_number;
    TextView tv_password;

    public void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_password = (TextView) findViewById(R.id.tv_password);

        btn_login.setOnClickListener(listener);
        btn_exit.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login:
                    Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_exit:
                    finish();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 设置为全屏模式（隐藏状态条）
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // ActionBar显示返回按钮
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initView();
    }

    // 返回按钮的功能
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}