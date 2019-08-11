package com.example.shopstore.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopstore.R;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Register extends AppCompatActivity implements View.OnClickListener {

    Button btn_send_code;
    EditText phone_text;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        btn_send_code=(Button)findViewById(R.id.btn_send_code);
        phone_text=(EditText)findViewById(R.id.phone_number);

        btn_send_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_send_code:{
                phoneNumber=phone_text.getText().toString();
                Pattern p=Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
                Matcher m=p.matcher(phoneNumber);
                if (m.matches()){


                }else{
                    Toast.makeText(Register.this,"手机号有误",Toast.LENGTH_SHORT).show();
                }
            };

        }
    }
}
