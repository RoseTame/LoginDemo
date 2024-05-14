package com.example.logindemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_exit;
    private EditText et_number;
    private EditText et_password;
    private CheckBox cb_pwd;
    private CheckBox cb_autoLogin;
    private SharedPreferences sp;

    public void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_login.setOnClickListener(listener);
        btn_exit.setOnClickListener(listener);

        et_number = (EditText) findViewById(R.id.et_number);
        et_password = (EditText) findViewById(R.id.et_password);

        cb_pwd = (CheckBox) findViewById(R.id.cb_pwd);
        cb_autoLogin = (CheckBox) findViewById(R.id.cb_autoLogin);

        cb_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cb_pwd.isChecked()) {
                    cb_autoLogin.setEnabled(true);
                } else {
                    cb_autoLogin.setEnabled(false);
                }
            }
        });

        sp = getSharedPreferences("mydata", MODE_PRIVATE);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login:
                    login();
                    break;
                case R.id.btn_exit:
                    finish();
                    break;
            }
        }
    };

    public void login() {
        String number = et_number.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (TextUtils.isEmpty(number) || TextUtils.isEmpty(password)) {
            Toast.makeText(
                    LoginActivity.this,
                    "QQ号或密码不能为空",
                    Toast.LENGTH_LONG
            ).show();
        } else {
            if (cb_pwd.isChecked()) { // 选中记住密码
                SharedPreferences.Editor editor = sp.edit();

                editor.putBoolean("remember", true);
                editor.putString("number", number);
                editor.putString("password", password);

                editor.apply();
            }

            if (cb_autoLogin.isChecked()) { // 选中自动登录
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("autoLogin", true);

                editor.apply();
            }

            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    public void readSP() {
        // 回显数据
        boolean rememberPassword = sp.getBoolean("remember", false);
        boolean autoLogin = sp.getBoolean("autoLogin", false);

        // 如果记住了密码,回显 QQ号和密码
        if (rememberPassword) {
            et_number.setText(sp.getString("number", null));
            et_password.setText(sp.getString("password", null));
            cb_pwd.setChecked(true);
        }

        // 如果选中了自动登录
        if (autoLogin) {
            cb_autoLogin.setChecked(true);
            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

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
        readSP();
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