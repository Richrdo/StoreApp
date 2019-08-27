package com.example.shopstore.data;

import android.content.*;
import android.text.TextUtils;

import com.example.shopstore.Control.UserInfo;

public class UserManage {
    private static UserManage instance;
    public UserManage(){

    }

    public static UserManage getInstance(){
        if (instance==null){
            instance=new UserManage();
        }
        return instance;
    }


    /*
    *保存自动登录的用户信息
     */
    public void saveUserInfo(Context context,String phoneNumber,String password){
        SharedPreferences sp=context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("PHONE_NUMBER",phoneNumber);
        editor.putString("PASSWORD",password);
        editor.apply();
    }

    /*
    *获取用户信息
     */
    private UserInfo getUserInfo(Context context){
        SharedPreferences sp=context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        UserInfo userInfo=new UserInfo();
        userInfo.setPhoneNumber(sp.getString("PHONE_NUMBER",""));
        userInfo.setPassword(sp.getString("PASSWORD",""));
        return userInfo;
    }

    /*
    *判断userInfo中是否有数据
     */
    public boolean hasUserInfo(Context context){
        UserInfo userInfo=getUserInfo(context);
        if (userInfo!=null){
            if ((!TextUtils.isEmpty(userInfo.getPhoneNumber()))&&(!TextUtils.isEmpty(userInfo.getPassword()))){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

}
