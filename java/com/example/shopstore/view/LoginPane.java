package com.example.shopstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopstore.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPane extends AppCompatActivity {
    Button btn_login;
    TextView tv_register;
    EditText account;
    EditText password;

    ResultSet rst;
    Connection conn;
    PreparedStatement pst;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_pane);

        password=(EditText)findViewById(R.id.password);
        account=(EditText)findViewById(R.id.account);
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
                login();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.e("MYTAG", "开始尝试连接");
//
//                        login();
//                    }
//                }).start();
            }
        });
    }

    public void login(){
        String phone= account.getText().toString();
        String psw=password.getText().toString();

        if (TextUtils.isEmpty(phone)){
            Toast.makeText(LoginPane.this,"账号不能为空",Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(psw)){
            Toast.makeText(LoginPane.this,"请输入密码",Toast.LENGTH_SHORT).show();
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    String ori_url="http://47.106.177.200:8080/store/user?phone="+phone+"&psw="+psw;
                    try{
                        URL url=new URL(ori_url);

                        //打开一个HttpURLConnection连接
                        HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                        Log.e( "MYTAG", "打开Http连接成功" );
                        //设置主机连接超时
                        urlConnection.setConnectTimeout(5*1000);
                        //设置读取数据超时
                        urlConnection.setReadTimeout(5*1000);
                        //设置为GET请求
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setUseCaches(true);
                        urlConnection.addRequestProperty("Connection","Keep-Alive");
                        //设置请求头信息，获取格式为json
                        urlConnection.setRequestProperty("Content-Type","application/json");
                        Log.e( "MYTAG", "url设置成功" );

                        //开始连接
                        urlConnection.connect();
                        Log.e( "MYTAG", "urlCode="+urlConnection.getResponseCode() );
                        //判断是否连接成功
                        if (urlConnection.getResponseCode()==200){
                            Log.e("MYTAG", "urlconnection连接成功！");
                            //获取返回的数据
                            String result=streamToString(urlConnection.getInputStream());
                            //解析JSON
                            JSONObject jsonObject=new JSONObject(result);
                            int code=jsonObject.getInt("code");
                            final String msg=jsonObject.getString("msg");
                            Log.e("MYTAG",msg);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginPane.this,msg,Toast.LENGTH_SHORT).show();
                                    Log.e("MYTAG","GET方式请求成功,"+result);
                                }
                            });
                            if (code==200){
                                Intent intent=new Intent(LoginPane.this,HomePage.class);
                                startActivity(intent);
                            }
                        }

                    }catch (IOException | JSONException e){
                        Log.e("MalformedURLException", "URL连接失败，MYTAG,MESSAGE="+e.getMessage() );
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }

    //将输入流转换为String
    public static String streamToString(InputStream stream){
        byte[] bytes=null;
        try{
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            byte[] buffer=new byte[1024];
            int len=0;
            while((len=stream.read(buffer))!=-1){
                outputStream.write(buffer,0,len);
            }
            outputStream.close();
            stream.close();
            bytes=outputStream.toByteArray();
        }catch (Exception e){
            Log.e("MYTAG", "streaToString失败，MESSAGE="+e.getMessage());

        }
        return new String(bytes);
    }

}
