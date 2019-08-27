package com.example.shopstore.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.shopstore.Control.Checkout;
import com.example.shopstore.R;
import com.example.shopstore.data.User;


public class UserFragment extends Fragment {
    private Activity mActivity;
    private Button btn_logoff;
    private TextView tv_user_name;
    private TextView tv_user_sex;
    private TextView tv_user_phone;
    private User user;

    SharedPreferences sp;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mActivity= (Activity) context;
        sp=mActivity.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_user,container,false);
        btn_logoff=view.findViewById(R.id.btn_logoff);
        tv_user_name=view.findViewById(R.id.tv_user_name);
        tv_user_phone=view.findViewById(R.id.tv_user_phone);
        tv_user_sex=view.findViewById(R.id.tv_user_sex);

        initUser();

        btn_logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoff();
            }
        });

        return view;
    }

    private void initUser(){
        Thread getUser=new Thread(new Runnable() {
            @Override
            public void run() {
                String url="http://47.106.177.200:8080/store/getUser?phone="+sp.getString("PHONE_NUMBER","");
                Log.e("MYTAG", "开始提交是连接为 "+url );
                user=Checkout.getUserInfo(url);
            }
        });
        getUser.start();
        try{
            getUser.join();
            Thread.sleep(20);
        }catch (InterruptedException e){
            Log.e("MYTAG", "interuptedexception e" );
        }
        Log.e("MYTAG", "开始设置面板" );
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_user_phone.setText(String.valueOf(user.getPhone()));
                tv_user_name.setText(user.getName());
                tv_user_sex.setText(user.getSex());
            }
        });
    }

    public void logoff(){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("PHONE_NUMBER",null);
        editor.putString("PASSWORD",null);
        editor.apply();
        Intent intent=new Intent(mActivity,LoginPane.class);
        startActivity(intent);
    }
}
