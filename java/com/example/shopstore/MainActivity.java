package com.example.shopstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserManager;

import com.example.shopstore.UI.login.LoginPane;

import com.example.shopstore.UI.login.LoginPane;
import com.example.shopstore.data.UserManage;

public class MainActivity extends AppCompatActivity {

    private static final int GO_HOME=0;
    private static final int GO_LOGIN=1;

//    跳转判断
    private  Handler  mHandler=new Handler(){
        @Override
    public void handleMessage(Message msg){
            switch(msg.what){
                case GO_HOME:
                    Intent intent=new Intent();
                    startActivity(intent);
                    finish();
                    break;
                case GO_LOGIN:
                    Intent intent2=new Intent(MainActivity.this,LoginPane.class);
                    startActivity(intent2);
                    finish();
                    break;
            }
        }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (UserManage.getInstance().hasUserInfo(this)){
            mHandler.sendEmptyMessageDelayed(GO_HOME,2000);
        }else {
            mHandler.sendEmptyMessageDelayed(GO_LOGIN,2000);
        }
    }
}
