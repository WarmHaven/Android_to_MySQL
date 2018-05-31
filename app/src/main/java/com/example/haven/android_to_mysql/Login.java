package com.example.haven.android_to_mysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText nameEt, passEt;
    TextInputLayout username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全螢幕
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);



        nameEt = (EditText) findViewById(R.id.name);
        passEt = (EditText) findViewById(R.id.password);

        username = (TextInputLayout)findViewById(R.id.usernameWraper);
        password = (TextInputLayout)findViewById(R.id.passwordWraper);





    }

    public void onClick(View v){
        // TextInputLayout可以取得包裹的EditText
        String name = username.getEditText().getText().toString().trim();
        String pass = password.getEditText().getText().toString().trim();
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


        String name1 = nameEt.getText().toString();
        String pass1 = passEt.getText().toString();
        String type = "Login";

        BackgroundWorker_Check_Login bk = new BackgroundWorker_Check_Login(this,this);
        bk.execute(type,name1,pass1);

    }



    public void register_onClick(View v){
        System.out.println("0");

        Intent intent = new Intent(this, Register.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplicationContext().startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}