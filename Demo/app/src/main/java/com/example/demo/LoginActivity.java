package com.example.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtPassword;
    private Button btn_login;
    private Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String password = edtPassword.getText().toString();
                BmobUser.loginByAccount(name, password, new LogInListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if(e ==null){
                            Toast.makeText(LoginActivity.this,"登录成功"+
                                    bmobUser.getUsername(),Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findViews() {
        edtName = findViewById(R.id.edt_name);
        edtPassword = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
    }
}
