package com.example.haven.android_to_mysql;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText nameEt, passEt, checkpassEt;
    TextInputLayout username,password,check_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);


        nameEt = (EditText) findViewById(R.id.name);
        passEt = (EditText) findViewById(R.id.password);
        checkpassEt = (EditText)findViewById(R.id.check_password);

        username = (TextInputLayout)findViewById(R.id.usernameWraper);
        password = (TextInputLayout)findViewById(R.id.passwordWraper);
        check_pwd = (TextInputLayout)findViewById(R.id.check_passwordWraper);


    }

    public void onClick(View view) {

        String name = username.getEditText().getText().toString().trim();
        String pass = password.getEditText().getText().toString().trim();
        String check_pass = check_pwd.getEditText().getText().toString().trim();

        String name1 = nameEt.getText().toString();
        String pass1 = passEt.getText().toString();
        String check_pass1 = checkpassEt.getText().toString();
        String type = "Register";

        if(TextUtils.isEmpty(name)){
            username.setError("帳號不能為空");
            return;
        }else{
            // 移除错误提示信息
            username.setErrorEnabled(false);
        }

        if(TextUtils.isEmpty(pass)){
            password.setError("密碼不能為空");
            return;
        }else{
            // 移除错误提示信息
            password.setErrorEnabled(false);
        }

        if(TextUtils.isEmpty(check_pass)){
            check_pwd.setError("確認密碼不能為空");

            return;
        }else if(!pass1.equals(check_pass1)){
            check_pwd.setError("確認密碼有誤");
            password.setError("確認密碼有誤");
            return;
        }else{
            // 移除错误提示信息
            check_pwd.setErrorEnabled(false);
        }

        BackgroundWorker_Insert_admins bk = new BackgroundWorker_Insert_admins(this,this);
        bk.execute(type,name1,pass1);





    }
}
