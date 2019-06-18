package com.example.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtPassword;
    private Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("注册页面");
        findViews();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtName.getText().toString();
                String pass = edtPassword.getText().toString();
                BmobUser bmobUser = new BmobUser();
                bmobUser.setUsername(userName);
                bmobUser.setPassword(pass);
                bmobUser.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if(e == null){
                            Toast.makeText(RegisterActivity.this, " 注册成功 " + bmobUser.getUsername(),
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
            }
        });
    }

    private void findViews() {
        edtName = findViewById(R.id.edt_name);
        edtPassword = findViewById(R.id.edt_password);
        btn_register = findViewById(R.id.btn_register);
    }
}
