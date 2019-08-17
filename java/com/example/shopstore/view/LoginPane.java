package com.example.shopstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopstore.R;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPane extends AppCompatActivity {
    Button btn_login;
    TextView tv_register;

    ResultSet rst;
    Connection conn;
    PreparedStatement pst;


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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("MYTAG", "开始尝试连接");
                        testJdbc();
                    }
                }).start();
            }
        });
    }

    public void testJdbc(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Log.e("MYTAG", "class加载完成");
            conn= DriverManager.getConnection("jdbc:mysql://47.106.177.200:3306/store?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8","ceaser","Q201023aaa");
            Log.e("MYTAG", "Connection加载完成");
            pst=conn.prepareStatement("use store");
            Log.e("MYTAG", "pst处理完成");
            rst=pst.executeQuery("select name from user where id=1");
            Log.e("MYTAG", "查询处理完成");

            while (rst.next()){
                Log.i("SUCCESS", "testJdbc: name= "+String.valueOf(rst.getObject(1)));
            }
        }catch (ClassNotFoundException | SQLException e){
            Log.e("ERROR", "testJdbc: "+e.getMessage() );
        }finally {
        }
    }

}
