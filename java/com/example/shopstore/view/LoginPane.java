package com.example.shopstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopstore.R;

public class LoginPane extends AppCompatActivity {
    Button btn_login;
    TextView tv_register;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_pane);

        btn_login=(Button)findViewById(R.id.btn_login);
        tv_register=(TextView)findViewById(R.id.tv_register);

//      跳转到注册界面
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginPane.this,Register.class);
                startActivity(intent);
            }
        });

//        跳转到商城主页
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginPane.this, HomePage.class);
                startActivity(intent);
            }
        });
    }

}
