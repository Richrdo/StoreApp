package com.example.shopstore.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import com.example.shopstore.Control.Checkout;
import com.example.shopstore.R;
import com.example.shopstore.data.User;
import com.example.shopstore.data.UserBean;
import com.google.gson.Gson;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Register extends AppCompatActivity implements View.OnClickListener {

    Button btn_register;
    EditText phone_text;
    EditText ed_name;
    EditText ed_sex;
    EditText ed_password;
    EditText ed_ano_password;
    String phoneNumber;
    String result=" 注册失败";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_register=findViewById(R.id.btn_register);
        phone_text=(EditText)findViewById(R.id.phone_number);
        ed_name=findViewById(R.id.user_name);
        ed_sex=findViewById(R.id.user_sex);
        ed_password=findViewById(R.id.pre_password);
        ed_ano_password=findViewById(R.id.next_password);


        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_register:{
                if (isValid()){
                    String user_password=ed_password.getText().toString().toLowerCase();
                    String user_name=ed_name.getText().toString().trim();
                    String user_sex=ed_sex.getText().toString();
                    long user_phone=Long.valueOf(phone_text.getText().toString().trim());
                    UserBean user=new UserBean();
                    user.setPassword(user_password);
                    user.setName(user_name);
                    user.setPhone(user_phone);
                    user.setSex(user_sex);
                    Thread thread=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            result=registerUser(user);
                        }
                    });
                    thread.start();
                    try{
                        thread.join();
                        Thread.sleep(20);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Register.this,result,Toast.LENGTH_LONG).show();
                    this.finish();
                }
            };

        }
    }

    public String registerUser(User user){

        Gson gson=new Gson();
        String json=gson.toJson(user);
        String url="http://47.106.177.200:8080/store/register";
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                result= Checkout.submit(url,json);
            }
        });
        thread.start();
        try{
            thread.join();
            Thread.sleep(20);
        }catch (InterruptedException e){
            Log.e("MYTAG", "registerUser: " );
        }
        return result;
    }

    public boolean isValid(){
        Boolean isMatch=false;
        phoneNumber=phone_text.getText().toString();
        Pattern p=Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m=p.matcher(phoneNumber);
        if (m.matches()&& !TextUtils.isEmpty(ed_name.getText())&&!TextUtils.isEmpty(ed_sex.getText())){
            if (!TextUtils.isEmpty(ed_password.getText())&&ed_password.getText().toString().equals(ed_ano_password.getText().toString())){
                isMatch=true;
            }else{
                Toast.makeText(Register.this,"密码不一致",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(Register.this,"手机号格式有误或姓名、性别不能为空",Toast.LENGTH_SHORT).show();
        }
        return isMatch;
    }
}
